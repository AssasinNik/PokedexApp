package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationV(
    val black_white: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.BlackWhite
)