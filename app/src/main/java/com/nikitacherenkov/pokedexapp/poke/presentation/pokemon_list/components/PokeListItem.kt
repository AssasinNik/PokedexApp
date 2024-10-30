package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.components

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nikitacherenkov.pokedexapp.poke.domain.Ability
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import com.nikitacherenkov.pokedexapp.poke.domain.Type
import com.nikitacherenkov.pokedexapp.ui.theme.PokedexAppTheme
import kotlinx.coroutines.Dispatchers

@Composable
fun PokeListItem(
    pokemonElement: PokemonInfo,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
){
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

    Row (
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonElement.imageUrl)
                .dispatcher(Dispatchers.IO)
                .memoryCacheKey(pokemonElement.imageUrl)
                .diskCacheKey(pokemonElement.imageUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = pokemonElement.name,
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = pokemonElement.name.replaceFirstChar { it.uppercase() },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Text(
                text = "Experience: " + pokemonElement.baseExperience,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = contentColor
            )
        }
    }
}