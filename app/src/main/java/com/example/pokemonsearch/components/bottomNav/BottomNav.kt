package com.example.pokemonsearch.components.bottomNav

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNav() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Search",
                        route = "search",
                        icon = Icons.Default.Search,
                    ),
                    BottomNavItem(
                        name = "Favorite",
                        route = "favorite",
                        icon = Icons.Default.Favorite
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                },
            )
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}
