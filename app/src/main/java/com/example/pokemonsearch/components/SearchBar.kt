package com.example.pokemonsearch.components

import android.renderscript.ScriptGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemonsearch.models.PokemonListViewModel

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pokemon Search",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.h6.fontSize
            )
            Spacer(modifier = Modifier.height(16.dp))
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
    var text by remember {
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
        leadingIcon = { Icon(Icons.Default.Search, "") },
        label = { Text("Search") },
        maxLines = 1,
        singleLine = true,
    )
}

