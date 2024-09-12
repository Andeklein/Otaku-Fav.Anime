package com.andre.otakufav_anime

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andre.otakufav_anime.data.AnimeRepository
import com.andre.otakufav_anime.data.remote.Anime
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

class MainViewModel(application: Application): AndroidViewModel(application) {

   private val animeRepository = AnimeRepository(application.applicationContext)

    private val _apiVersion = MutableLiveData<Int>()
    val apiVersion: LiveData<Int>
        get() = _apiVersion

   private val _anime = MutableLiveData<List<Anime>>()
    val anime: LiveData<List<Anime>>
        get() = _anime

    init {
        getApiVersion()
        fetchAnime()
    }

    fun getApiVersion(){
        viewModelScope.launch {
            val apiVersion = animeRepository.getApiVersion()
            _apiVersion.value = apiVersion
            Log.i("Anime","getApiVersion: $apiVersion")
        }

    }
    fun fetchAnime(){
        viewModelScope.launch {
            try {
                val response = animeRepository.fetchAnimeFromApi()
                logging().info { " fetching anime: ${response.animes.size}" }
            } catch (e: Exception) {
               logging().info { "Error fetching anime: ${e.message}" }
            }

        }
    }

}
