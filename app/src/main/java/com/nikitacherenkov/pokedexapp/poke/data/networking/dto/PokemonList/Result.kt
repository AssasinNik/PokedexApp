package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.PokemonList

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val name: String,
    val url: String
)