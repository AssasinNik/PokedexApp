package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitacherenkov.pokedexapp.core.domain.util.onError
import com.nikitacherenkov.pokedexapp.core.domain.util.onSuccess
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonDataSource
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokeListViewModel(
    private val pokeDataSource: PokemonDataSource
): ViewModel() {

    private val _state = MutableStateFlow(PokeListState())
    val state = _state
        .onStart {
            loadPokemons()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PokeListState()
        )

    fun onAction(action: PokeListAction){
        when(action){
            is PokeListAction.OnPokeClick -> {

            }
        }
    }

    private fun loadPokemons(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val pokemonList: MutableList<PokemonInfo> = mutableListOf()
            pokeDataSource
                .getPokemonsList()
                .onSuccess { pokemons ->
                    pokemons.map { pokemon ->
                        pokeDataSource.getPokemon(pokemon.url.trimEnd('/').split("/").last())
                            .onSuccess { pokemonInfo ->
                                pokemonList+=pokemonInfo
                            }
                            .onError { error ->
                                _state.update { it.copy(isLoading = false) }
                            }
                    }
                    if(pokemonList.isNotEmpty()){
                        _state.update { it.copy(
                            isLoading = false,
                            pokemons = pokemonList
                        ) }
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}