package com.example.pokemonsearch.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonsearch.components.SearchBar
import com.example.pokemonsearch.components.pokemonList.PokemonListEntry

@Composable
fun SearchScreen(navController : NavController) {
    Column(){
        SearchBar()
        Spacer(modifier = Modifier.height(1.dp))
        PokemonListEntry(navController = navController)
    }
}
