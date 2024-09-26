package com.andre.otakufav_anime.data.remote

import android.content.Context
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.local.AnimeDatabase
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

    suspend fun getLikedAnime(): List<AnimeApiResponse> {
        return animeDao.getLikedAnime()
    }

    suspend fun loadDataToDatabase() {
        if (animeDao.getAllAnime().isEmpty()) {
            val anime = fetchAnimeFromApi()
            animeDao.insertAllAnime(anime)
        }
    }

    suspend fun getRandomAnime(): AnimeApiResponse? {
        return animeDao.getRandomAnime()
    }

    suspend fun updateAnime(anime: AnimeApiResponse){
        animeDao.updateAnime(anime)
    }

    suspend fun saveAnimeToDatabase() {

        try {
            val responseVersion = getApiVersion()
            val responseAnime = fetchAnimeFromApi()

            if (responseVersion == 1.0){
                responseAnime.forEach {
                    animeDao.getRandomAnime()
                }
            }
            logging().info { " fetching anime: ${responseAnime.size}" }
        } catch (e: Exception) {
            logging().info { "Error fetching anime: ${e.message}" }
        }
    }
}

