package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitacherenkov.pokedexapp.core.domain.util.onError
import com.nikitacherenkov.pokedexapp.core.domain.util.onSuccess
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonDataSource
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext

class PokeListViewModel(
    private val pokeDataSource: PokemonDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(PokeListState())
    val state = _state
        .onStart {
            loadPokemons(_state.value.offset)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _events = Channel<PokeListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: PokeListAction) {
        when (action) {
            is PokeListAction.OnPokeClick -> {
                _state.update {
                    it.copy(selectedPokemon = action.poke)
                }
            }
        }
    }

    fun loadMorePokemons() {
        val currentOffset = _state.value.offset + 10
        _state.update {
            it.copy(
                isPaginated = true,
                offset = _state.value.offset + 10
            )
        }
        loadPokemons(currentOffset)
    }

    private fun loadPokemons(offset: Int) {
        viewModelScope.launch {
            if (offset == 0) {
                _state.update { it.copy(isLoading = true, isPaginated = false) }
            } else {
                _state.update { it.copy(isPaginated = true, isLoading = false) }
            }

            val pokemonList = mutableListOf<PokemonInfo>()

            try {
                val result = withContext(Dispatchers.IO) {
                    pokeDataSource.getPokemonsList(offset = offset)
                }

                result.onSuccess { pokemons ->
                    val deferredResults = pokemons.map { pokemon ->
                        async(Dispatchers.IO) {
                            val id = pokemon.url.trimEnd('/').split("/").last()
                            pokeDataSource.getPokemon(id)
                        }
                    }

                    val results = deferredResults.awaitAll()

                    results.forEach { pokemonResult ->
                        pokemonResult.onSuccess { pokemonInfo ->
                            pokemonList += pokemonInfo
                        }.onError { error ->
                            _state.update { it.copy(isLoading = false, isPaginated = false) }
                            _events.send(PokeListEvent.Error(error))
                        }
                    }

                    if (pokemonList.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                isPaginated = false,
                                pokemons = it.pokemons + pokemonList
                            )
                        }
                    } else {
                        _state.update { it.copy(isLoading = false, isPaginated = false) }
                    }
                }.onError { error ->
                    _state.update { it.copy(isLoading = false, isPaginated = false) }
                    _events.send(PokeListEvent.Error(error))
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isPaginated = false) }
            }
        }
    }
}