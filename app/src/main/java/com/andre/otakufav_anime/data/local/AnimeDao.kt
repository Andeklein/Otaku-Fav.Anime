package com.andre.otakufav_anime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andre.otakufav_anime.data.remote.AnimeRoom
import com.andre.otakufav_anime.data.remote.CharacterRoom


@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(anime: List<AnimeRoom>)

    @Query("SELECT * FROM table_anime")
    suspend fun getAllAnime(): List<AnimeRoom>

    @Update
    suspend fun updateAnime(anime: AnimeRoom)

    @Update
    suspend fun updateCharacter(character: CharacterRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterRoom>)

    @Query("SELECT * FROM table_character")
    suspend fun getAllCharacters(): List<CharacterRoom>

    @Query("SELECT * FROM table_anime WHERE isFeatured = 0 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomAnime(): AnimeRoom?

    @Query("SELECT * FROM table_anime WHERE isLiked = 1")
    suspend fun getLikedAnime(): List<AnimeRoom>

    @Query("SELECT * FROM table_character WHERE isLikedCharacter = 1")
    suspend fun getLikedCharacters(): List<CharacterRoom>

    @Query("SELECT * FROM table_character WHERE isFeaturedCharacter = 0 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCharacter(): CharacterRoom?


}