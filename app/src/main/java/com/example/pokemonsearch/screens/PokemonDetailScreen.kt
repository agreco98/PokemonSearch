package com.example.pokemonsearch.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokemonsearch.data.remote.responses.Ability
import com.example.pokemonsearch.data.remote.responses.Move
import com.example.pokemonsearch.data.remote.responses.Pokemon
import com.example.pokemonsearch.data.remote.responses.Type
import com.example.pokemonsearch.util.Resource
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
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp + 160.dp / 2f, bottom = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .align(Alignment.BottomCenter),
                loadingModifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
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
fun PokemonDetailTopBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(CircleShape)
                .clickable {
                    navController.popBackStack()
                }
                .padding(12.dp)
                .size(24.dp)



        )
        Icon(
            imageVector = Icons.Default.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(CircleShape)
                .clickable {

                }
                .padding(12.dp)
                .size(24.dp)

        )
    }
}

@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when(pokemonInfo) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokemonInfo = pokemonInfo.data!!,
                modifier = modifier
                    .offset(y = (-20).dp)
            )
        }
        is Resource.Error -> {
            Text(
                text = pokemonInfo.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier,
            )
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

@Composable
fun PokemonDetailSection(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(Modifier.height(4.dp))
        Text(
            text = pokemonInfo.name.capitalize(Locale.ROOT),
            style = MaterialTheme.typography.h5
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "N° ${pokemonInfo.id}",
            style = MaterialTheme.typography.body2
        )
        Spacer(Modifier.height(8.dp))
        PokemonTypeChip(types = pokemonInfo.types)
        Spacer(Modifier.height(24.dp))
            PokemonDetailDataSection(
                pokemonWeigth = pokemonInfo.weight,
                pokemonHeight = pokemonInfo.height,
                pokemonBaseExperience = pokemonInfo.base_experience
            )
    }
}

@Composable
fun PokemonTypeChip(
    types: List<Type>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp),
    ) {
        for (type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
            ) {
                Text(
                    text = type.type.name.capitalize(),
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun PokemonDetailDataSection(
    pokemonWeigth: Int,
    pokemonHeight: Int,
    pokemonBaseExperience: Int
) {
    val pokemonWeigthInKg = remember {
        (pokemonWeigth * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        (pokemonHeight * 100f) / 1000f
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 28.dp)
    ) {
        PokemonDetailDataItem(
            dataValue = pokemonWeigthInKg,
            dataUnit = " kg",
            dataIcon = Icons.Default.LineWeight,
            dataMeasure = "Weigth"
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(32.dp)
        )
        PokemonDetailDataItem(
            dataValue = pokemonHeightInMeters,
            dataUnit = " mts",
            dataIcon = Icons.Default.Height,
            dataMeasure = "Height"
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(32.dp)
        )
        PokemonDetailDataBaseExperience(
            dataValue = pokemonBaseExperience,
            dataUnit = " exp",
            dataIcon = Icons.Default.Star,
            dataBaseExperience = "Experience"
        )
    }
}

@Composable
fun PokemonDetailDataItem(
    dataValue: Float,
    dataUnit: String,
    dataIcon: ImageVector,
    dataMeasure: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = dataIcon,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                modifier = modifier
                        .size(20.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "$dataValue$dataUnit",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = dataMeasure,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 2.dp)
        )
    }
}

@Composable
fun PokemonDetailDataBaseExperience(
    dataValue: Int,
    dataUnit: String,
    dataIcon: ImageVector,
    dataBaseExperience: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = dataIcon,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                modifier = modifier
                    .size(20.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "$dataValue$dataUnit",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = dataBaseExperience,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 2.dp)
        )

    }
}

@Composable
fun PokemonDetailDataItemMoves(
    abilities: List<Ability>,
    dataMeasure: String,
) {
    Column {
        for (ability in abilities) {
            Text(
                text = ability.ability.name.capitalize(),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = dataMeasure,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption
        )
    }
}


