package com.andre.otakufav_anime.data.model

import com.example.animeapp.data.model.Anime

// Diese Klasse ist nur für die Bündelung von Animes gedacht, und benutzt die Klasse der Room Implementierung

data class AnimeResponse(

    val animes: List<Anime>

)