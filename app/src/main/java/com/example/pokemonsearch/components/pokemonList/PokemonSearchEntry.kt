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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.request.ImageRequest
import com.example.pokemonsearch.models.PokemonListViewModel
import com.example.pokemonsearch.models.PokemonSearchListEntry
import com.google.accompanist.coil.rememberCoilPainter


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
        items(itemCount) {
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
               entry = entries[rowIndex],
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
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Surface(
        color = Color.White,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(
                        "detail_pokemon/${dominantColor.toArgb()}/${entry.pokemonName}"
                    )
                },
        ) {
            PokemonImage(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Box {
                    val painter = rememberCoilPainter(entry.imageUrl)
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
                        text = "#${entry.number}",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(Modifier.height(8.dp))
                    PokemonChip(type = entry.type)
                }
            }
    }
}

@Composable
private fun PokemonImage(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(Modifier
        .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
        .size(width = 80.dp, height = 80.dp),
        RoundedCornerShape(50.dp),
        color = MaterialTheme.colors.background
    ) {
        content()
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


