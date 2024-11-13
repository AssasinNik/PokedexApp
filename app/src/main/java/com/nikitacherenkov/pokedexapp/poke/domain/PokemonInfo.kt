package com.nikitacherenkov.pokedexapp.poke.domain


data class PokemonInfo(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val abilities: List<Ability>,
    val types: List<Type>,
    val imageUrl: String,
    val stats : List<Stat>
)

data class Ability(
    val name: String,
    val isHidden: Boolean
)

data class Type(
    val name: String
)
data class Stat(
    val stat: Int,
    val name: String
)