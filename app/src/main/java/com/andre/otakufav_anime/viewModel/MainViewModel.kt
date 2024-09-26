package com.andre.otakufav_anime.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andre.otakufav_anime.data.remote.AnimeRepository
import com.andre.otakufav_anime.data.remote.AnimeApiResponse
import com.example.animeapp.data.model.Anime
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

   private val animeRepository = AnimeRepository(application.applicationContext)

    private val _apiVersion = MutableLiveData<Int>()
    val apiVersion: LiveData<Int>
        get() = _apiVersion

   private val _anime = MutableLiveData<List<Anime>>()
    val anime: LiveData<List<Anime>>
        get() = _anime

    private val _randomAnime = MutableLiveData<AnimeApiResponse>()
    val randomAnime: LiveData<AnimeApiResponse>
        get() = _randomAnime

    private val _isLikedAnime = MutableLiveData<List<AnimeApiResponse>>()
    val isLikedAnime: LiveData<List<AnimeApiResponse>>
        get() = _isLikedAnime

    private val _currentAnime = MutableLiveData<AnimeApiResponse>()
    val currentAnime: LiveData<AnimeApiResponse>
        get() = _currentAnime

    fun loadLikedAnimes() {
        viewModelScope.launch {
            _isLikedAnime.value = animeRepository.getLikedAnime()
        }
    }

    init {
        loadDataToDatabase()
    }

    fun loadDataToDatabase() {
        viewModelScope.launch {
            animeRepository.loadDataToDatabase()
            getRandomAnime()
        }
    }

    fun getRandomAnime(){
        viewModelScope.launch {
            val randomAnime = animeRepository.getRandomAnime()
            Log.d("Anime","getRandomAnime: $randomAnime")
            _randomAnime.value = animeRepository.getRandomAnime()
        }
    }

    fun updateIsLikedAnime() {
        _randomAnime.value?.isLiked = !_randomAnime.value?.isLiked!!
        viewModelScope.launch {
            animeRepository.updateAnime(_randomAnime.value!!)
        }
        getRandomAnime()
    }
    fun trashAnime() {
        _randomAnime.value?.isTrashed = true
        getRandomAnime()
        viewModelScope.launch {
            animeRepository.updateAnime(_randomAnime.value!!)
        }
    }

    fun setCurrentAnime(anime: AnimeApiResponse) {
        _currentAnime.value = anime
    }
}
