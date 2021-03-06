package com.example.pokemonsearch.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.CatchingPokemon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemonsearch.viewModels.PokemonListViewModel

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.TwoTone.CatchingPokemon,
                    contentDescription = "Logo",
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Pokemon Search",
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            InputSearch() {
                viewModel.searchPokemonList(it)
            }
        }
    }
}

@Composable
fun InputSearch(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        },
        leadingIcon = { Icon(Icons.Default.Search, "search") },
        label = { Text("Search") },
        maxLines = 1,
        singleLine = true,
    )
}

