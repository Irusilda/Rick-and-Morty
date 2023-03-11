package com.example.rick_and_morty.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(
    var results: List<ResultCharacter>
) : Parcelable

@Parcelize
data class ResultCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
): Parcelable

@Parcelize
   data class Location(
    val name: String,
    val url: String
): Parcelable

@Parcelize
data class Origin(
    val name: String,
    val url: String
): Parcelable