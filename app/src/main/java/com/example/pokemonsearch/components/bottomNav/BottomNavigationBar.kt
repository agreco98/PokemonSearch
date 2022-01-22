package com.example.pokemonsearch.components.bottomNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pokemonsearch.ui.theme.PokemonSearchTheme

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = modifier
                .padding(16.dp)
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.large)
                .fillMaxWidth()
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface,
                    icon = {
                        Column(
                            horizontalAlignment = CenterHorizontally
                        ) {
                            if(selected) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = MaterialTheme.typography.overline.fontSize
                                )
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }

                        }
                    },
                )
            }
        }
}

