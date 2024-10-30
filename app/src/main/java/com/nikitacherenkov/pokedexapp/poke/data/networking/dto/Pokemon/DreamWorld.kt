package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class DreamWorld(
    val front_default: String,
    val front_female: String?
)