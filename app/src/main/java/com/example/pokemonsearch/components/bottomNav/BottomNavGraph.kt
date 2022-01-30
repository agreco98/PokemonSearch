package com.example.pokemonsearch.components.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.pokemonsearch.screens.FavoriteScreen
import com.example.pokemonsearch.screens.PokemonDetailScreen
import com.example.pokemonsearch.screens.SearchScreen
import java.util.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreen(navController = navController)
        }
        composable("favorite") {
            FavoriteScreen(navController = navController)
        }
        composable(
            "detail_pokemon/{pokemonName}",
                    arguments = listOf(
                        navArgument("pokemonName") {
                            type = NavType.StringType
                        }
                    )) {
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailScreen(
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                navController = navController
            )
        }
    }
}