package com.nikitacherenkov.pokedexapp.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nikitacherenkov.pokedexapp.core.presentation.util.ObserveAsEvents
import com.nikitacherenkov.pokedexapp.core.presentation.util.toString
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListAction
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListEvent
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListScreen
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.PokeListViewModel
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_screen.PokemonScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptivePokemonListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: PokeListViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { event ->
        when(event){
            is PokeListEvent.Error->{
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                PokeListScreen(
                    state = state,
                    onAction = {action ->
                        viewModel.onAction(action)
                        when(action){
                            is PokeListAction.OnPokeClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }
                        }
                    },
                    viewModel = viewModel
                )
            }
        },
        detailPane = {
            AnimatedPane {
                PokemonScreen(state = state)
            }
        },
        modifier = modifier
    )
}