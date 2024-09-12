package com.andre.otakufav_anime.data.remote

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class AnimeResponse(
    val animes: List<Anime>
)

@Serializable
data class Anime(

    val anime: String,
    val info: String,
    val genre: List<String>,
    val banner: String,
    val image: String,
    val trailer: Trailer,
    val characters: List<Character>
)

@Serializable
data class Trailer(
    val deutsch: String,
    val japanisch: String
)

@Serializable
data class Character(
    val name: String,
    val description: String,
    val image: String,
    @SerialName("fähigkeiten") val faehigkeiten: List<String>,
)

