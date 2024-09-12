package com.andre.otakufav_anime.data

import android.content.Context
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.local.AnimeDatabase
import com.andre.otakufav_anime.data.remote.Anime
import com.andre.otakufav_anime.data.remote.AnimeApi
import com.andre.otakufav_anime.data.remote.AnimeResponse

class AnimeRepository(

    val context: Context

    ) {
    private val api: AnimeApi = AnimeApi

    private val animeDao: AnimeDao = AnimeDatabase.getDatabase(context).animeDao()

    suspend fun getApiVersion(): Int {
        return api.service.getVersion().version
    }

       // Holt Animes von der API
        suspend fun fetchAnimeFromApi(): AnimeResponse {
            return api.service.getAnime()

        }
    }

