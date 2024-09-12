package com.andre.otakufav_anime.data.model

import androidx.room.TypeConverter

class Convert {
    @TypeConverter
    fun genreToString(string: String): List<String> = string.split(",")
    @TypeConverter
    fun stringToGenre(list: List<String>): String = list.joinToString(",")


}