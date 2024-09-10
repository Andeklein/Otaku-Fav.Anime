package com.andre.otakufav_anime.data

import androidx.lifecycle.LiveData
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.remote.AnimeApiService
import com.example.animeapp.data.model.Anime

class AnimeRepository(
    private val animeDao: AnimeDao,
    private val apiService: AnimeApiService
    ) {
        // Holt Animes von der API
        suspend fun fetchAnimesFromApi(): LiveData<List<Anime>> {
            return apiService.getAnimes()
        }

        // Fügt einen Anime zu Room hinzu
        suspend fun insertAnime(anime: Anime) {
            animeDao.insertAnime(anime)
        }


    }
