package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import com.nikitacherenkov.pokedexapp.core.domain.util.NetworkError

sealed interface PokeListEvent {
    data class Error(val error: NetworkError): PokeListEvent
}