package com.andre.otakufav_anime.data

import android.content.Context
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.local.AnimeDatabase
import com.andre.otakufav_anime.data.remote.AnimeApi
import com.example.animeapp.data.model.Anime

class AnimeRepository(

    val context: Context

    ) {
    private val api: AnimeApi = AnimeApi

    private val animeDao: AnimeDao = AnimeDatabase.getDatabase(context).animeDao()



    suspend fun getApiVersion(): Int {
        return api.service.getVersion().version
    }

     /*   // Holt Animes von der API
        suspend fun fetchAnimeFromApi(): List<Anime> {
            return api.service.getAnime()
        } */

        // Fügt einen Anime zu Room hinzu
        suspend fun insertAnime(anime: Anime) {
            animeDao.insertAnime(anime)
        }

    }

