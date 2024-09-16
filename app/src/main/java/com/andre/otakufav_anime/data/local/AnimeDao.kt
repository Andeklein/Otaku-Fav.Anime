package com.andre.otakufav_anime.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andre.otakufav_anime.data.model.IsLikedAnime
import com.andre.otakufav_anime.data.remote.AnimeApiResponse
import com.example.animeapp.data.model.Anime

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIsLikedAnime(anime: IsLikedAnime)

    @Delete
    suspend fun deleteIsLikedAnime(anime: IsLikedAnime)

    @Query("SELECT * FROM table_anime_liked")
    fun getAllAnime(): List<IsLikedAnime>
}