package com.andre.otakufav_anime.data.model

import androidx.room.TypeConverter
import com.andre.otakufav_anime.data.remote.Trailer
import com.andre.otakufav_anime.data.remote.Character


class Convert {

    @TypeConverter
    fun stringToList(string: String): List<String> = string.split(",")
    @TypeConverter
    fun listToString(list: List<String>): String = list.joinToString(",")
    @TypeConverter
    fun trailerToString(trailer: Trailer): String = trailer.deutsch +","+ trailer.japanisch
    @TypeConverter
    fun stringToTrailer(string: String): Trailer {
        val list = string.split(",")
        return Trailer(list[0],list[1])
    }
    @TypeConverter
    fun characterToString(character: Character): String {
        return character.name +","+ character.description +","+ character.image +","+ listToString(character.faehigkeiten)
    }
    @TypeConverter
    fun stringToCharacter(string: String): Character {
        val list = string.split(",")
        return Character(list[0],list[1],list[2],stringToList(list[3]))
    }
    @TypeConverter
    fun stringToCharacterList(string: String): List<Character>{
        val list = string.split(",")
        return list.map { stringToCharacter(it) }
    }
    @TypeConverter
    fun characterListToString(list: List<Character>): String {
        return list.joinToString(",") { characterToString(it) }
    }

}