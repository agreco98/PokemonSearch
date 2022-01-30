package com.example.pokemonsearch.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokemonsearch.components.pokemonDetail.PokemonDetailStateWrapper
import com.example.pokemonsearch.components.pokemonDetail.PokemonDetailTopBar
import com.example.pokemonsearch.data.remote.responses.Pokemon
import com.example.pokemonsearch.data.remote.responses.Type
import com.example.pokemonsearch.util.Resource
import com.example.pokemonsearch.util.parseStatToAbbr
import com.example.pokemonsearch.util.parseTypeToColor
import com.example.pokemonsearch.viewModels.PokemonDetailViewModel
import java.util.*

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value

    Column {
        PokemonDetailTopBar(
            navController = navController,
        )
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            PokemonDetailStateWrapper(
                pokemonInfo = pokemonInfo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp + 160.dp / 2f, bottom = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
            )
            PokemonDetailImage(
                modifier = Modifier
                    .align(Alignment.TopCenter)
            ) {
                Box {
                    if(pokemonInfo is Resource.Success) {
                        pokemonInfo.data?.sprites?.let {
                            val painter = rememberImagePainter(it.front_default)
                            Image(
                                painter = painter,
                                contentDescription = pokemonInfo.data.name,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonDetailImage(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(Modifier
        .padding(top = 16.dp)
        .size(width = 160.dp, height = 160.dp)
        .clip(RoundedCornerShape(100.dp))
        .border(4.dp, Color.White, CircleShape),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}

