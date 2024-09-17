package com.andre.otakufav_anime.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andre.otakufav_anime.data.remote.AnimeApiResponse


@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnime(anime: List<AnimeApiResponse>)

    @Query("SELECT * FROM table_anime")
    suspend fun getAllAnime(): List<AnimeApiResponse>

    @Update
    suspend fun updateAnime(anime: AnimeApiResponse)

    @Query("SELECT * FROM table_anime WHERE isFeatured = 0 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomAnime(): AnimeApiResponse?

}