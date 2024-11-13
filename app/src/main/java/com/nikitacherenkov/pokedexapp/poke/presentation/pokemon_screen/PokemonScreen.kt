package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_screen

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListState

import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_screen.components.PokemonCard
import com.nikitacherenkov.pokedexapp.ui.theme.PokemonName
import kotlinx.coroutines.Dispatchers

@Composable
fun PokemonScreen(
    state: PokeListState,
    modifier: Modifier = Modifier
){
    val pokemonElement = state.selectedPokemon
    val contentColor: Color
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
        contentColor = if (isSystemInDarkTheme()){
            Color.White
        }else{
            Color.Black
        }
    }
    else{
        contentColor = Color.White
    }
    if (state.isLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        Column(
            modifier = modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (pokemonElement != null) {
                Text(
                    text = pokemonElement.name.replaceFirstChar { it.uppercase() } + "#" + pokemonElement.id.toString().padStart(3,'0'),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = contentColor
                )
                PokemonCard(pokemonElement)
            }
        }
    }
}