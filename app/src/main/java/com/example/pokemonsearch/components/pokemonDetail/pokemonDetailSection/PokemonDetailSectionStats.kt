package com.example.pokemonsearch.components.pokemonDetail.pokemonDetailSection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemonsearch.data.remote.responses.Pokemon
import com.example.pokemonsearch.util.parseStatToAbbr

@Composable
fun PokemonDetailSectionStats(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
) {
    var currentPercent = statValue / statMaxValue.toFloat()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 2.dp).fillMaxWidth()
    ) {
        Box(
            Modifier.width(32.dp)
        ) {
            Text(
                text = statName,
                style = MaterialTheme.typography.caption,
            )
        }
        Spacer(Modifier.width(8.dp))
        LinearProgressIndicator(
            progress = currentPercent,
            color = Color(0xFFFFCC01),
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth()
        )
    }
}

@Composable
fun PokemonDetailSectionBaseStats(
    pokemonInfo: Pokemon,
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.base_stat }
    }
    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Base stats",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))

        for(i in pokemonInfo.stats.indices) {
            val stat = pokemonInfo.stats[i]
            PokemonDetailSectionStats(
                statName = parseStatToAbbr(stat),
                statValue = stat.base_stat,
                statMaxValue = maxBaseStat,
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}