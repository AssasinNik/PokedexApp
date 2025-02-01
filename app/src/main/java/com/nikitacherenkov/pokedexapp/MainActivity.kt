package com.nikitacherenkov.pokedexapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikitacherenkov.pokedexapp.core.navigation.AdaptivePokemonListDetailPane
import com.nikitacherenkov.pokedexapp.core.presentation.util.ObserveAsEvents
import com.nikitacherenkov.pokedexapp.core.presentation.util.toString
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListEvent
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListScreen
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListViewModel
import com.nikitacherenkov.pokedexapp.ui.theme.PokedexAppTheme
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            PokedexAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) { innerPadding ->
                        val viewModel = koinViewModel<PokeListViewModel>()
                        AdaptivePokemonListDetailPane(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
