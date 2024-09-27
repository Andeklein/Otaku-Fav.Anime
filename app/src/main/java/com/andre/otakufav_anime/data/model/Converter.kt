package com.andre.otakufav_anime.data.model

import android.util.Log
import androidx.room.TypeConverter
import com.andre.otakufav_anime.data.remote.Trailer
import com.andre.otakufav_anime.data.remote.CharacterRoom

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

    //region start
    @TypeConverter
    fun characterRoomToString(character: CharacterRoom): String {
        return character.name +"|"+ character.description +"|"+ character.image +"|"+ listToString(character.faehigkeiten)
    }
    @TypeConverter
    fun stringToCharacterRoom(string: String): CharacterRoom {
        val list = string.split("|")
        Log.e("Converter","stringToCharacterRoom: $list")
         try {
            return CharacterRoom(list[0],list[1],list[2],stringToList(list[3]),stringToBoolean(list[4]),stringToBoolean(list[5]),stringToBoolean(list[6]))
        } catch (e: Exception) {
            Log.e("Converter","stringToCharacterRoom: $list")
        }
        return CharacterRoom()
    }
    @TypeConverter
    fun characterRoomListToString(list: List<CharacterRoom>): String {
        Log.e("Converter","characterRoomListToString: $list")
        return list.joinToString("Seperator") { characterRoomToString(it) }
    }
    @TypeConverter
    fun stringToCharacterRoomList(string: String): List<CharacterRoom>{
        val list = string.split("Seperator")
        return list.map { stringToCharacterRoom(it) }
    }

    fun stringToBoolean(string: String): Boolean {
        if (string == "0") {
            return false
        }
        return true
    }
//endregion
}