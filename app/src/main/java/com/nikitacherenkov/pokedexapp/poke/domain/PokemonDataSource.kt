package com.nikitacherenkov.pokedexapp.poke.domain

import com.nikitacherenkov.pokedexapp.core.domain.util.NetworkError
import com.nikitacherenkov.pokedexapp.core.domain.util.Result

interface PokemonDataSource {
    suspend fun getPokemonsList(): Result<List<PokemonElement>, NetworkError>
    suspend fun getPokemon(name: String): Result<PokemonInfo, NetworkError>
}