package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GameIndice(
    val game_index: Int,
    val version: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Version
)