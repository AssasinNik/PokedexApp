package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitacherenkov.pokedexapp.core.domain.util.onError
import com.nikitacherenkov.pokedexapp.core.domain.util.onSuccess
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonDataSource
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokeListViewModel(
    private val pokeDataSource: PokemonDataSource
): ViewModel() {

    init {
        loadPokemons(0)
    }

    private val _state = MutableStateFlow(PokeListState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PokeListState()
        )

    private val _events = Channel<PokeListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: PokeListAction){
        when(action){
            is PokeListAction.OnPokeClick -> {
                _state.update {
                    it.copy(selectedPokemon = action.poke)
                }
            }
        }
    }


    fun loadMorePokemons() {
        Log.d("Load More", "${_state.value.offset}")
        loadPokemons(_state.value.offset)
        _state.update {
            it.copy(
                isPaginated = true,
                offset = it.offset + 10
            )
        }
    }

    private fun loadPokemons(offset: Int) {
        viewModelScope.launch (context = Dispatchers.IO){
            if(offset == 0){
                _state.update { it.copy(isLoading = true) }
            }
            val pokemonList: MutableList<PokemonInfo> = mutableListOf()

            pokeDataSource.getPokemonsList(offset = offset)
                .onSuccess { pokemons ->
                    pokemons.map { pokemon ->
                        val pokemonInfo = async {
                            pokeDataSource.getPokemon(pokemon.url.trimEnd('/').split("/").last())
                        }.await()
                        pokemonInfo.onSuccess { pokemonInfo ->
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
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false, isPaginated = false) }
                    _events.send(PokeListEvent.Error(error))
                }
        }
    }
}