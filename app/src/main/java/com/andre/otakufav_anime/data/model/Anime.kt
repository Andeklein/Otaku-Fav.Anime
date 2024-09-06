package com.example.animeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_anime")
data class Anime(
    @PrimaryKey
    val id: Int,
    val title: String,
    val imageUrl: String
)

