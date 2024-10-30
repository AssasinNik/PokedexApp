package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.MoveLearnMethod,
    val version_group: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.VersionGroup
)