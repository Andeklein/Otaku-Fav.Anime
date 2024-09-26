package com.andre.otakufav_anime.data.model

import android.util.Log
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
        return character.name +"|"+ character.description +"|"+ character.image +"|"+ listToString(character.fähigkeiten)
    }
    @TypeConverter
    fun stringToCharacter(string: String): Character {
        val list = string.split("|")
        Log.e("Converter","stringToCharacter: $list")
        return Character(list[0],list[1],list[2],stringToList(list[3]))
    }
    @TypeConverter
    fun characterListToString(list: List<Character>): String {
        Log.e("Converter","characterListToString: $list")
        return list.joinToString("Seperator") { characterToString(it) }
    }
    @TypeConverter
    fun stringToCharacterList(string: String): List<Character>{
        val list = string.split("Seperator")
        return list.map { stringToCharacter(it) }
    }

}