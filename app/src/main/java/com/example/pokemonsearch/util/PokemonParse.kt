package com.example.pokemonsearch.util

import androidx.compose.ui.graphics.Color
import com.example.pokemonsearch.data.remote.responses.Stat
import com.example.pokemonsearch.data.remote.responses.Type
import com.example.pokemonsearch.ui.theme.*
import java.util.*

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoision
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase()) {
        "hp" -> "Hp"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SAtk"
        "special-defense" -> "SDef"
        "speed" -> "Spd"
        else -> ""
    }
}