package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.PokemonList

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)