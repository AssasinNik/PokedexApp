package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import androidx.compose.runtime.Immutable
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo

@Immutable
data class PokeListState(
    val isLoading: Boolean = false,
    val pokemons: List<PokemonInfo> = emptyList(),
    val selectedPokemon: PokemonInfo? =null,
    val offset: Int = 0,
    val isPaginated: Boolean = false
)