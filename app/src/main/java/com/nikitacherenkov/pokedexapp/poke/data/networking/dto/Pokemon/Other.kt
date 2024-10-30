package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class Other(
    val dream_world: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.DreamWorld,
    val home: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Home,
    val showdown: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Showdown
)