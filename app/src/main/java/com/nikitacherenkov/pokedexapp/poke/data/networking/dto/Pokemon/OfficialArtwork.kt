package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String
)