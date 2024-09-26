package com.andre.otakufav_anime.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "table_anime")
data class AnimeApiResponse(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val anime: String = "",
    val info: String = "",
    val genre: List<String> = listOf(),
    val banner: String = "",
    val image: String = "",
    val trailer: Trailer = Trailer("", ""),
    val characters: List<Character> = listOf(),
    var isLiked: Boolean = false,
    var isTrashed: Boolean = false,
    var isFeatured: Boolean = false
)

@Serializable
data class Trailer(
    val deutsch: String = "",
    val japanisch: String = ""
)

@Serializable
data class Character(
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val fähigkeiten: List<String> = listOf(),
)

