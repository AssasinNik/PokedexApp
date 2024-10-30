package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.StatX
)