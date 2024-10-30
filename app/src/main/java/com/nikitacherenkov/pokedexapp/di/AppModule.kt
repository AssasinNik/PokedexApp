package com.nikitacherenkov.pokedexapp.di

import com.nikitacherenkov.pokedexapp.core.data.networking.HttpClientFactory
import com.nikitacherenkov.pokedexapp.poke.data.networking.RemotePokemonDataSource
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonDataSource
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }
    singleOf(::RemotePokemonDataSource).bind<PokemonDataSource>()

    viewModelOf(::PokeListViewModel)
}