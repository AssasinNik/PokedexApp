package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationIi(
    val crystal: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Crystal,
    val gold: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Gold,
    val silver: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Silver
)