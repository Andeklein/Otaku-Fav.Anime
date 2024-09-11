package com.andre.otakufav_anime

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andre.otakufav_anime.data.AnimeRepository
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.local.AnimeDatabase
import com.andre.otakufav_anime.data.remote.AnimeAPI
import com.andre.otakufav_anime.data.remote.AnimeApiService
import com.example.animeapp.data.model.Anime
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

   // private val animeDatabase = AnimeDatabase.getDatabase(application)
    private val animeRepository = AnimeRepository(AnimeAPI)

    val _anime = MutableLiveData<List<Anime>>()
    val anime: LiveData<List<Anime>>
        get() = _anime

    init {
        getAnime()
    }

    fun getAnime() {
        viewModelScope.launch {
            _anime.value = animeRepository.fetchAnimeFromApi()
        }
    }
}
