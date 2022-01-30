package com.example.pokemonsearch.components.pokemonDetail.pokemonDetailSection.pokemonDetailSectionData

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.LineWeight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailSectionData(
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
        PokemonDetailSectionDataItem(
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
        PokemonDetailSectionDataItem(
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
        PokemonDetailDataSectionBaseExperience(
            dataValue = pokemonBaseExperience,
            dataUnit = " exp",
            dataIcon = Icons.Default.Star,
            dataBaseExperience = "Experience"
        )
    }
}