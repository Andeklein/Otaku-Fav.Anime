package com.andre.otakufav_anime.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_anime_liked")
data class IsLikedAnime(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
)
