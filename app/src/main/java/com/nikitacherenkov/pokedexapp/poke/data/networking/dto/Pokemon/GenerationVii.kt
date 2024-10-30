package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationVii(
    val icons: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Icons,
    val ultra_sun_ultra_moon: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.UltraSunUltraMoon
)