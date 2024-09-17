package com.andre.otakufav_anime.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.local.AnimeDatabase
import com.andre.otakufav_anime.data.model.IsLikedAnime
import com.andre.otakufav_anime.data.remote.AnimeApi
import com.andre.otakufav_anime.data.remote.AnimeApiResponse
import com.example.animeapp.data.model.Anime
import org.lighthousegames.logging.logging

class AnimeRepository(

    val context: Context

) {
    private val api: AnimeApi = AnimeApi

    private val animeDao: AnimeDao = AnimeDatabase.getDatabase(context).animeDao()

    private suspend fun getApiVersion(): Double {
        return api.retrofitService.getVersion().version
    }

    // Holt Animes von der API
    private suspend fun fetchAnimeFromApi(): List<AnimeApiResponse> {
        return api.retrofitService.getAnimes()
    }

    suspend fun loadDataToDatabase() {
        val anime = fetchAnimeFromApi()
        animeDao.insertAllAnime(anime)
    }

    fun getRandomAnime(): LiveData<AnimeApiResponse> {
        return animeDao.getRandomAnime()
    }

   /* suspend fun saveAnimeToDatabase() {

        try {
            val responseVersion = getApiVersion()
            val responseAnime = fetchAnimeFromApi()

            if (responseVersion == 1.0){
                responseAnime.forEach { anime ->
                    animeDao.insertAnime()
                }
            }
            logging().info { " fetching anime: ${responseAnime.size}" }
        } catch (e: Exception) {
            logging().info { "Error fetching anime: ${e.message}" }
        }
    }

    */

}

