package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationI(
    val red_blue: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.RedBlue,
    val yellow: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Yellow
)