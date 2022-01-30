package com.example.pokemonsearch.components.pokemonDetail.pokemonDetailSection.pokemonDetailSectionData

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailSectionDataItem(
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
fun PokemonDetailDataSectionBaseExperience(
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