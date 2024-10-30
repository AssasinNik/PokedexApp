package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationIii(
    val emerald: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Emerald,
    val firered_leafgreen: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.FireredLeafgreen,
    val ruby_sapphire: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.RubySapphire
)