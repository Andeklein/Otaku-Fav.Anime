package com.andre.otakufav_anime.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andre.otakufav_anime.data.remote.AnimeRepository
import com.andre.otakufav_anime.data.remote.AnimeRoom
import com.andre.otakufav_anime.data.remote.CharacterRoom
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

   private val animeRepository = AnimeRepository(application.applicationContext)

    private val _apiVersion = MutableLiveData<Int>()
    val apiVersion: LiveData<Int>
        get() = _apiVersion

   private val _anime = MutableLiveData<List<AnimeRoom>>()
    val anime: LiveData<List<AnimeRoom>>
        get() = _anime

    private val _randomAnime = MutableLiveData<AnimeRoom>()
    val randomAnime: LiveData<AnimeRoom>
        get() = _randomAnime

    private val _isLikedAnime = MutableLiveData<List<AnimeRoom>>()
    val isLikedAnime: LiveData<List<AnimeRoom>>
        get() = _isLikedAnime

    private val _isLikedCharacter = MutableLiveData<List<CharacterRoom>>()
    val isLikedCharacter: LiveData<List<CharacterRoom>>
        get() = _isLikedCharacter

    private val _currentAnime = MutableLiveData<AnimeRoom>()
    val currentAnime: LiveData<AnimeRoom>
        get() = _currentAnime

    private val _currentCharacter = MutableLiveData<CharacterRoom>()
    val currentCharacter: LiveData<CharacterRoom>
        get() = _currentCharacter

    fun loadLikedCharacters() {
        viewModelScope.launch {
            _isLikedCharacter.value = animeRepository.getLikedCharacters()
        }
    }

    fun loadLikedAnimes() {
        viewModelScope.launch {
            _isLikedAnime.value = animeRepository.getLikedAnime()
        }
    }

    init {
        loadDataToDatabase()
        viewModelScope.launch {
            animeRepository.saveAnimeToDatabase()
        }
    }
// neu
    fun loadDataToDatabase() {
        viewModelScope.launch {
            getRandomAnime()
            getRandomCharacter()
        }
    }

    fun getRandomAnime(){
        viewModelScope.launch {
            val randomAnime = animeRepository.getRandomAnime()
            Log.d("Anime","getRandomAnime: $randomAnime")
            _randomAnime.value = animeRepository.getRandomAnime()
        }
    }

    fun getRandomCharacter(){
        viewModelScope.launch {
            val randomCharacter = animeRepository.getRandomCharacter()
            Log.d("Anime","getRandomCharacter: $randomCharacter")
            _currentCharacter.value = animeRepository.getRandomCharacter()
        }
    }

    fun updateIsLikedAnime() {
        _randomAnime.value?.isLiked = !_randomAnime.value?.isLiked!!
        viewModelScope.launch {
            animeRepository.updateAnime(_randomAnime.value!!)
        }
        getRandomAnime()
    }

    fun updateIsLikedCharacter() {
        _currentCharacter.value?.isLikedCharacter = !_currentCharacter.value?.isLikedCharacter!!
        viewModelScope.launch {
            animeRepository.updateCharacter(_currentCharacter.value!!)
        }
        getRandomCharacter()
    }

    fun trashAnime() {
        _randomAnime.value?.isTrashed = true
        getRandomAnime()
        viewModelScope.launch {
            animeRepository.updateAnime(_randomAnime.value!!)
        }
    }

    fun setCurrentAnime(anime: AnimeRoom) {
        _currentAnime.value = anime
    }
    fun setCurrentCharacter(character: CharacterRoom) {
        _currentCharacter.value = character
    }
}
