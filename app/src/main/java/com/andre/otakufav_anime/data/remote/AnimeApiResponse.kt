package com.andre.otakufav_anime.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class AnimeApiResponse(

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
    val faehigkeiten: List<String>,
)

