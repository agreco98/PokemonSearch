package com.example.pokemonsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.pokemonsearch.navigation.PokemonSearchNavGraph
import com.example.pokemonsearch.ui.theme.PokemonSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonSearchTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    PokemonSearchNavGraph(navController = navController)
                }
            }
        }
    }
}
