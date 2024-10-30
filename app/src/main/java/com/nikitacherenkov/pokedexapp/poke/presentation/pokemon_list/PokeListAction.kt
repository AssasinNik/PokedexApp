package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo

sealed interface PokeListAction {
    data class OnPokeClick(val poke: PokemonInfo): PokeListAction
}