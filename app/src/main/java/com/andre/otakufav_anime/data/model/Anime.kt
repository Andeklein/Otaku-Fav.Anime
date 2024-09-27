package com.example.animeapp.data.model

import androidx.room.PrimaryKey
import kotlinx.serialization.json.Json

data class Anime(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val anime: String,
    val info: String,
    val genre: List<String>,
    val banner: String,
    val image: String,
    val trailer: String,
    val characters: String,
    var isLiked: Boolean = false
){
   fun toTrailerObject(): Trailer {
        val germanTrailer = trailer.split(",")[0]
        val japaneseTrailer = trailer.split(",")[1]
        return Trailer(germanTrailer, japaneseTrailer)
    }

    fun toCharacterObject(): List<Character> {
        return Json.decodeFromString<List<Character>>(characters)
    }
}

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
