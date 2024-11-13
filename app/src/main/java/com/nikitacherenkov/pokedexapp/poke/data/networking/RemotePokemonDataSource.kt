package com.nikitacherenkov.pokedexapp.poke.data.networking

import com.nikitacherenkov.pokedexapp.core.data.networking.constructURL
import com.nikitacherenkov.pokedexapp.core.data.networking.safeCall
import com.nikitacherenkov.pokedexapp.core.domain.util.NetworkError
import com.nikitacherenkov.pokedexapp.core.domain.util.Result
import com.nikitacherenkov.pokedexapp.core.domain.util.map
import com.nikitacherenkov.pokedexapp.poke.data.mappers.toListPokemonElement
import com.nikitacherenkov.pokedexapp.poke.data.mappers.toPokemonInfo
import com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Pokemon
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonDataSource
import com.nikitacherenkov.pokedexapp.poke.data.networking.dto.PokemonList.PokemonList
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonElement
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemotePokemonDataSource(
    private val httpClient: HttpClient
): PokemonDataSource {
    override suspend fun getPokemonsList(offset: Int): Result<List<PokemonElement>, NetworkError> {
        return safeCall<PokemonList>{
            httpClient.get(
                urlString = constructURL("/pokemon?limit=10&offset=$offset")
            )
        }.map { response ->
            response.results.toListPokemonElement()
        }
    }

    override suspend fun getPokemon(id: String): Result<PokemonInfo, NetworkError> {
        return safeCall<Pokemon>{
            httpClient.get(
                urlString = constructURL("pokemon/$id")
            )
        }.map { response ->
            response.toPokemonInfo()
        }
    }

}