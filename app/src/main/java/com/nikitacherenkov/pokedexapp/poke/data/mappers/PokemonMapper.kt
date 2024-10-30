package com.nikitacherenkov.pokedexapp.poke.data.mappers

import com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Pokemon
import com.nikitacherenkov.pokedexapp.poke.data.networking.dto.PokemonList.Result
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonElement
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo

fun List<Result>.toListPokemonElement(): List<PokemonElement> {
    return this.map { result ->
        PokemonElement(
            name = result.name,
            url = result.url
        )
    }
}

fun Pokemon.toPokemonInfo(): PokemonInfo{
    return PokemonInfo(
        id = id,
        name = name,
        height = height,
        weight = weight,
        baseExperience = base_experience,
        abilities = abilities,
        types = types,
        imageUrl = sprites.front_default
    )
}