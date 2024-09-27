package com.andre.otakufav_anime.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "table_anime")
data class AnimeRoom(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val anime: String = "",
    val info: String = "",
    val genre: List<String> = listOf(),
    val banner: String = "",
    val image: String = "",
    val trailer: Trailer = Trailer("", ""),
    var isLiked: Boolean = false,
    var isTrashed: Boolean = false,
    var isFeatured: Boolean = false,
    val characters: List<CharacterRoom> = listOf()
)

@Serializable
@Entity(tableName = "table_character")
data class CharacterRoom(

    @PrimaryKey
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val faehigkeiten: List<String> = listOf(),
    var isLikedCharacter: Boolean = false,
    var isTrashedCharacter: Boolean = false,
    var isFeaturedCharacter: Boolean = false
)

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
        return AnimeRoom(
            anime = anime,
            info = info,
            genre = genre,
            banner = banner,
            image = image,
            trailer = trailer,
            isLiked = isLiked,
            isTrashed = isTrashed,
            isFeatured = isFeatured,
            characters = characters.map { it.toCharacterRoom() }
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
            faehigkeiten = fähigkeiten,
            isLikedCharacter = false,
            isTrashedCharacter = false,
            isFeaturedCharacter = false
        )
    }
}

