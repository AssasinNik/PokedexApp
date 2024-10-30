package com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val abilities: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Ability>,
    val base_experience: Int,
    val cries: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Cries,
    val forms: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Form>,
    val game_indices: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.GameIndice>,
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Move>,
    val name: String,
    val order: Int,
    val species: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Species,
    val sprites: com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Sprites,
    val stats: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Stat>,
    val types: List<com.nikitacherenkov.pokedexapp.poke.data.networking.dto.Pokemon.Type>,
    val weight: Int
)