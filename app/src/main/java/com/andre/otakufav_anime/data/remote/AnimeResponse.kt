package com.andre.otakufav_anime.data.remote

import kotlinx.serialization.Serializable

// Diese Klasse ist nur für die Bündelung von Animes gedacht, und benutzt die Klasse der Room Implementierung

@Serializable
data class AnimeResponse(
    val animes: List<Anime>
)