package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class GenerationIv(
    val diamond_pearl: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.DiamondPearl,
    val heartgold_soulsilver: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.HeartgoldSoulsilver,
    val platinum: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Platinum
)