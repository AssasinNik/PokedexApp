package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class RubySapphire(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)