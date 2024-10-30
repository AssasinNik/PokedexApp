package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.PokemonList

import kotlinx.serialization.Serializable

@Serializable
data class PokemonList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)