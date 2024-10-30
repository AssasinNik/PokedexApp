package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

data class Ability(
    val ability: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)