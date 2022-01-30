package com.example.pokemonsearch.components.pokemonDetail.pokemonDetailSection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemonsearch.components.pokemonDetail.pokemonDetailSection.pokemonDetailSectionData.PokemonDetailSectionData
import com.example.pokemonsearch.data.remote.responses.Pokemon
import java.util.*

@Composable
fun PokemonDetailSection(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 100.dp)
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
        PokemonDetailSectionChip(types = pokemonInfo.types)
        Spacer(Modifier.height(24.dp))
        PokemonDetailSectionData(
            pokemonWeigth = pokemonInfo.weight,
            pokemonHeight = pokemonInfo.height,
            pokemonBaseExperience = pokemonInfo.base_experience
        )
        Spacer(Modifier.height(32.dp))
        PokemonDetailSectionBaseStats(
            pokemonInfo = pokemonInfo
        )
    }
}
