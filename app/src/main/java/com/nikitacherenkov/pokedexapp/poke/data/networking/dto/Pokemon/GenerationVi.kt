package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationVi(
    val omegaruby_alphasapphire: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.OmegarubyAlphasapphire,
    val x_y: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.XY
)