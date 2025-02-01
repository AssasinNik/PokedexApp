package com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.nikitacherenkov.pokedexapp.core.presentation.util.toString
import com.nikitacherenkov.pokedexapp.poke.presentation.pokemon_list.components.PokeListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Composable
fun PokeListScreen(
    state: PokeListState,
    modifier: Modifier = Modifier,
    onAction: (PokeListAction) -> Unit,
    viewModel: PokeListViewModel
){
    val lazyListState = rememberLazyListState()
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .map { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisibleItem to totalItems
            }
            .distinctUntilChanged()
            .collect { (lastVisibleItem, totalItems) ->
                Log.d("problem", "$state")
                if (lastVisibleItem == totalItems-1  && !state.isLoading && !state.isPaginated && state.offset != 1300 ) {
                    viewModel.loadMorePokemons()
                }
            }
    }
    if(state.isLoading){
        Box (
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }

    } else{
        LazyColumn(
            state = lazyListState,
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.pokemons){ pokemon ->
                PokeListItem(
                    pokemonElement = pokemon,
                    onClick = {
                        onAction(PokeListAction.OnPokeClick(pokemon))
                    },
                    modifier =  Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
            item {
                if (state.isPaginated) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else{
                    Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
                }
            }
        }
    }
}