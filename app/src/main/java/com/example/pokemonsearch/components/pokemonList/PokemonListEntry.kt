package com.example.pokemonsearch.components.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokemonsearch.viewModels.PokemonListViewModel
import com.example.pokemonsearch.models.PokemonSearchListEntry

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
    val isSearching by remember { viewModel.isSearching }

    val itemCount = if(pokemonList.size % 2 == 0) {
        pokemonList.size / 2
    } else {
        pokemonList.size / 2 - 1
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(itemCount) {
            if (it >= itemCount - 1 && !endReached && !isLoading && !isSearching) {
                viewModel.loadPokemonPaginated()
            }
            PokemonSearchEntryRow(rowIndex = it,
                entries = pokemonList,
                navController = navController)
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadPokemonPaginated()
            }
        }
    }
}

@Composable
fun PokemonSearchEntryRow(
    entries: List<PokemonSearchListEntry>,
    rowIndex: Int,
    navController: NavController
) {
   Column {
       Row() {
           PokemonSearchEntry(
               entry = entries[rowIndex],
               navController = navController
           )
   }
    Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun PokemonSearchEntry(
    entry: PokemonSearchListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = Color.White,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(
                        "detail_pokemon/${entry.pokemonName}"
                    )
                },
        ) {
            PokemonImage(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Box {
                    val painter = rememberImagePainter(entry.imageUrl)
                    Image(
                        painter = painter,
                        contentDescription = entry.pokemonName,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
            }
                Spacer(Modifier.width(24.dp))
                Column(
                    Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = entry.pokemonName,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "N?? ${entry.number}",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
    }
}

@Composable
fun PokemonImage(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(Modifier
        .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
        .size(width = 72.dp, height = 72.dp),
        RoundedCornerShape(100.dp),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            ) {
            Text(text = "Retry")
        }
    }
}


