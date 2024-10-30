package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.components.PokeListItem

@Composable
fun PokeListScreen(
    state: PokeListState,
    modifier: Modifier = Modifier
){
    if(state.isLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else{
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.pokemons){ pokemon ->
                PokeListItem(
                    pokemonElement = pokemon,
                    onClick = { /*TODO*/ },
                    modifier =  Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}