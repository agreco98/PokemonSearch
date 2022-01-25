package com.example.pokemonsearch.models

import com.example.pokemonsearch.data.remote.responses.Type

data class PokemonSearchListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int,
)