package com.andre.otakufav_anime.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true // Unbekannte Schlüssel ignorieren
}

@Serializable
@Entity(tableName = "table_anime")
data class AnimeRoom(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val anime: String = "",
    val info: String = "",
    val genre: String = "",
    val banner: String = "",
    val image: String = "",
    val trailer: String = "",
    var isLiked: Boolean = false,
    var isTrashed: Boolean = false,
    var isFeatured: Boolean = false,
    val characters: String = ""
) {
    val genreList: List<String>
        get() = json.decodeFromString(genre)

    val trailerObject: Trailer
        get() = json.decodeFromString(trailer)

    val characterList: List<CharacterRoom>
        get() = json.decodeFromString(characters)
}

@Serializable
@Entity(tableName = "table_character")
data class CharacterRoom(

    @PrimaryKey
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val faehigkeiten: String = "",
    var isLikedCharacter: Boolean = false,
    var isTrashedCharacter: Boolean = false,
    var isFeaturedCharacter: Boolean = false
) {
    val faehigkeitenList: List<String>
        get() = json.decodeFromString(faehigkeiten)
}

data class AnimeApiResponse(

    val anime: String = "",
    val info: String = "",
    val genre: List<String> = listOf(),
    val banner: String = "",
    val image: String = "",
    val trailer: Trailer = Trailer("", ""),
    var isLiked: Boolean = false,
    var isTrashed: Boolean = false,
    var isFeatured: Boolean = false,
    val characters: List<Character> = listOf()
){
    fun toAnimeRoom(): AnimeRoom {
        val x = json.encodeToString(characters)
        return AnimeRoom(
            anime = anime,
            info = info,
            genre = json.encodeToString(genre),
            banner = banner,
            image = image,
            trailer = json.encodeToString(trailer),
            isLiked = isLiked,
            isTrashed = isTrashed,
            isFeatured = isFeatured,
            characters = json.encodeToString( characters )
        )
    }
}

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
    val fähigkeiten: List<String> = listOf()
){
    fun toCharacterRoom(): CharacterRoom {
        return CharacterRoom(
            name = name,
            description = description,
            image = image,
            faehigkeiten = json.encodeToString(fähigkeiten),
            isLikedCharacter = false,
            isTrashedCharacter = false,
            isFeaturedCharacter = false
        )
    }
}

