package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val slot: Int,
    val type: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.TypeX
)