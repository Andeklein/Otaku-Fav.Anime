package com.example.animeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_anime")
data class Anime(
    @PrimaryKey
    val anime: String,
    val info: String,
    val genre: List<String>,
    val banner: String,
    val image: String,
    val trailer: Trailer
)

data class Trailer(
    val deutsch: String,
    val japanisch: String
)

