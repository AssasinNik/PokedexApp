package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(
    val name: String,
    val url: String
)