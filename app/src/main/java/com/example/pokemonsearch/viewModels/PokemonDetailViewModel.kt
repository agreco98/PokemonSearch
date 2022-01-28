package com.example.pokemonsearch.viewModels

import androidx.lifecycle.ViewModel
import com.example.pokemonsearch.data.remote.responses.Pokemon
import com.example.pokemonsearch.repository.PokemonRepository
import com.example.pokemonsearch.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}