package com.example.animeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_anime")

data class Anime(

    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    val anime: String,
    val info: String,
    val genre: List<String>,
    val banner: String,
    val image: String,
    val trailer: Trailer,
    val characters: List <Character>
)

data class Trailer(
    val deutsch: String,
    val japanisch: String
)

data class Character(
    val name: String,
    val description: String,
    val image: String,
    val fähigkeiten: List<String>,
)