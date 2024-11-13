package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_screen.components

import android.os.Build
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import kotlinx.coroutines.Dispatchers

@Composable
fun PokemonCard(
    pokemon: PokemonInfo?
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
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState(), true)
    ){
        if (pokemon != null){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AnimatedContent(
                    targetState = pokemon.imageUrl,
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    ), label = ""
                ) { icon ->
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(icon)
                            .dispatcher(Dispatchers.IO)
                            .memoryCacheKey(icon)
                            .diskCacheKey(icon)
                            .diskCachePolicy(CachePolicy.ENABLED)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Crop,
                        filterQuality = FilterQuality.None,
                        modifier = Modifier
                            .size(270.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = contentColor
            )
        ) {
            if (pokemon != null){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Overview",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Height: " + pokemon.height + "cm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = contentColor
                    )
                    Text(
                        text = "Weight: " + pokemon.weight + "kg",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = contentColor
                    )
                    Text(
                        text = "Experience: " + pokemon.baseExperience,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Stats",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = contentColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    pokemon.stats.map {
                        Text(
                            text = it.name.replaceFirstChar { it.uppercase() } + ": " + it.stat,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = contentColor
                        )
                    }
                }
            }
        }
    }
}