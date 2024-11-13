package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_screen.components

import android.os.Build
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.nikitacherenkov.pokedexapp.R
import com.nikitacherenkov.pokedexapp.poke.domain.PokemonInfo
import kotlinx.coroutines.Dispatchers
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.vector.ImageVector
import com.nikitacherenkov.pokedexapp.poke.domain.Stat
import com.nikitacherenkov.pokedexapp.poke.domain.Type

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonCard(
    pokemon: PokemonInfo?
) {
    if (pokemon == null) return

    val primaryType = pokemon.types.firstOrNull() ?: "normal"
    val contentColor: Color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isSystemInDarkTheme()) Color.White else Color.Black
    } else {
        Color.White
    }

    GradientBackground() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState(), enabled = true)
            ) {

                // Изображение и типы
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    AnimatedContent(
                        targetState = pokemon.imageUrl,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        label = ""
                    ) { icon ->
                        PokemonImage(imageUrl = icon, primaryType = primaryType.toString())
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    val maxWidth = this.maxWidth
                    val threshold = 600.dp // Пороговая ширина для переключения раскладки

                    if (maxWidth >= threshold) {
                        // Раскладка в строку
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Top
                        ) {
                            PokemonInfoCard(
                                title = stringResource(id = R.string.overview),
                                content = {
                                    OverviewSection(pokemon = pokemon, contentColor = contentColor)
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .requiredWidth(280.dp) // Минимальная ширина
                            )
                            PokemonInfoCard(
                                title = stringResource(id = R.string.stats),
                                content = {
                                    StatsSection(stats = pokemon.stats, contentColor = contentColor, onStatClick = {})
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .requiredWidth(280.dp) // Минимальная ширина
                            )
                        }
                    } else {
                        // Раскладка в столбец
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            PokemonInfoCard(
                                title = stringResource(id = R.string.overview),
                                content = {
                                    OverviewSection(pokemon = pokemon, contentColor = contentColor)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .requiredHeight(200.dp) // Минимальная высота
                            )
                            PokemonInfoCard(
                                title = stringResource(id = R.string.stats),
                                content = {
                                    StatsSection(stats = pokemon.stats, contentColor = contentColor, onStatClick = {})
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .requiredHeight(200.dp) // Минимальная высота
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradientBackground(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        content()
    }
}

@Composable
fun PokemonImage(imageUrl: String, primaryType: String) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .dispatcher(Dispatchers.IO)
            .memoryCacheKey(imageUrl)
            .diskCacheKey(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = "pokemon image",
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.None,
        modifier = Modifier
            .size(290.dp)
    )
}


@Composable
fun PokemonInfoCard(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            content()
        }
    }
}

@Composable
fun OverviewSection(pokemon: PokemonInfo, contentColor: Color) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        OverviewItem(
            icon = Icons.Default.Height,
            label = stringResource(id = R.string.height),
            value = "${pokemon.height} cm",
            contentColor = contentColor
        )
        OverviewItem(
            icon = Icons.Default.FitnessCenter,
            label = stringResource(id = R.string.weight),
            value = "${pokemon.weight} kg",
            contentColor = contentColor
        )
        OverviewItem(
            icon = Icons.Default.Star,
            label = stringResource(id = R.string.experience),
            value = "${pokemon.baseExperience}",
            contentColor = contentColor
        )
    }
}
@Composable
fun OverviewItem(icon: ImageVector, label: String, value: String, contentColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label: $value",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = contentColor
        )
    }
}

@Composable
fun StatsSection(stats: List<Stat>, contentColor: Color, onStatClick: (Stat) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        stats.forEach { stat ->
            StatItem(name = stat.name, value = stat.stat)
        }
    }
}

@Composable
fun StatItem(name: String, value: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = (value / 100f).coerceIn(0f, 1f),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}
