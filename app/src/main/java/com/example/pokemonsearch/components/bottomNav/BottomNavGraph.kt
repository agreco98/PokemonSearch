package com.example.pokemonsearch.components.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonsearch.screens.FavoriteScreen
import com.example.pokemonsearch.screens.SearchScreen

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
            FavoriteScreen()
        }
        composable(
            "detail_pokemon/{dominantColor}/{pokemonName}",
                    arguments = listOf(
                        navArgument("dominantColor") {
                            type = NavType.IntType
                        },
                        navArgument("pokemonName") {
                            type = NavType.StringType
                        }
                    )) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
        }
    }
}