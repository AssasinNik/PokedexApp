package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

data class Move(
    val move: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.MoveX,
    val version_group_details: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.VersionGroupDetail>
)