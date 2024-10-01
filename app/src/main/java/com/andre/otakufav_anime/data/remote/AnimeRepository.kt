package com.andre.otakufav_anime.data.remote

import android.content.Context
import com.andre.otakufav_anime.data.local.AnimeDao
import com.andre.otakufav_anime.data.local.AnimeDatabase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging

class AnimeRepository(

    val context: Context

) {
    private val api: AnimeApi = AnimeApi

    private val animeDao: AnimeDao = AnimeDatabase.getDatabase(context).animeDao()

    private suspend fun getApiVersion(): Double {
        return api.retrofitService.getVersion().version
    }

    private suspend fun fetchAnimeFromApi(): List<AnimeRoom> {
        return api.retrofitService.getAnimes()
    }

    suspend fun getLikedAnime(): List<AnimeRoom> {
        return animeDao.getLikedAnime()
    }

    suspend fun getRandomCharacter(): CharacterRoom? {
        return animeDao.getRandomCharacter()
    }

   suspend fun getLikedCharacters(): List<CharacterRoom> {
        return animeDao.getLikedCharacters()
    }

    suspend fun getRandomAnime(): AnimeRoom? {
        return animeDao.getRandomAnime()
    }

    suspend fun updateAnime(anime: AnimeRoom){
        animeDao.updateAnime(anime)
    }

    suspend fun updateCharacter(character: CharacterRoom){
        animeDao.updateCharacter(character)
    }

    suspend fun saveAnimeToDatabase() {

        try {
            val responseVersion = getApiVersion()
            val responseAnime = fetchAnimeFromApi()

            if (responseVersion == 1.0){
                val animeList = responseAnime.map {
                    AnimeRoom(
                        anime = it.anime,
                        info = it.info,
                        genre = Json.encodeToString(it.genre) ,
                        banner = it.banner,
                        image = it.image,
                        trailer = Json.encodeToString(it.trailer),
                        characters = Json.encodeToString(it.characters)
                    )
                }
                val charList = animeList.flatMap {anime ->
                    anime.characterList.map {
                        CharacterRoom(
                            name = it.name,
                            description = it.description,
                            image = it.image,
                            faehigkeiten = Json.encodeToString(it.faehigkeiten)
                        )
                    }
                }
                animeDao.insertAllAnime(animeList)
                animeDao.insertAllCharacters(charList)

                logging().info { " animeResult: ${animeList.size}" }
                logging().info { " characterResult: ${charList.size}" }
            }
        } catch (e: Exception) {
            logging().info { "Error fetching anime: ${e.message}" }
        }
    }

}

