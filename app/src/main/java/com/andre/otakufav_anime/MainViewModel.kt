package com.andre.otakufav_anime

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andre.otakufav_anime.data.AnimeRepository
import com.andre.otakufav_anime.data.model.IsLikedAnime
import com.example.animeapp.data.model.Anime
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

    }

    fun insertIsLikedAnime(anime: IsLikedAnime){
        viewModelScope.launch {
            animeRepository.insertIsLikedAnime(anime)
        }
    }

    fun deleteIsLikedAnime(anime: IsLikedAnime){
        viewModelScope.launch {
            animeRepository.deleteIsLikedAnime(anime)
        }
    }

    fun getAllAnime(): List<IsLikedAnime>{
        return animeRepository.getAllAnime()
    }

}
