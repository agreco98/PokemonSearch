package com.example.pokemonsearch.components.pokemonList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.example.pokemonsearch.models.PokemonListViewModel
import com.example.pokemonsearch.models.PokemonSearchListEntry
import com.google.accompanist.coil.CoilImage


@Composable
fun PokemonListEntry(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    val itemCount = pokemonList.size

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        val itemCount = if(pokemonList.size % 2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount) {
            if(it >= itemCount - 1 && !endReached) {
                viewModel.loadPokemonPaginated()
            }
            PokemonSearchRow(rowIndex = it, entries = pokemonList, navController = navController)
        }
    }
}


@Composable
fun PokemonSearchRow(
    entries: List<PokemonSearchListEntry>,
    rowIndex: Int,
    navController: NavController
) {
   Column {
       Row() {
           PokemonSearchEntry(
               entry = entries[rowIndex * 2],
               navController = navController
           )
   }
    Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PokemonSearchEntry(
    entry: PokemonSearchListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Row(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    "detail_pokemon/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Column {
            CoilImage(
                request = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
                    .target {
                        viewModel.calculateDominantColor(it) { color ->
                            dominantColor = color
                        }
                    }
                    .build(),
                contentDescription = entry.pokemonName,
                fadeIn = true,
                modifier = Modifier
                    .size(48.dp)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )
            }
        }
        Spacer(Modifier.width(24.dp))
        Column {
            Text(
                text = "${entry.pokemonName} + ${entry.number}",
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(8.dp))
            PokemonChip(type = entry.type)
        }
    }
}

@Composable
fun PokemonChip(
    type: String
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = MaterialTheme.shapes.large
    ) {
            Text(
                text = type,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(8.dp)
            )
    }
}

@Composable
fun ChipGroup(
    types: List<PokemonSearchListEntry>,
) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow {
            items(types) {
                PokemonChip(
                   type = it.type
                )
            }
        }
    }
}


