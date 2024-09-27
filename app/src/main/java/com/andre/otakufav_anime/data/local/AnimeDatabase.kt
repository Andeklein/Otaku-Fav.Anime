package com.andre.otakufav_anime.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andre.otakufav_anime.data.model.Convert
import com.andre.otakufav_anime.data.remote.AnimeRoom
import com.andre.otakufav_anime.data.remote.CharacterRoom

@Database(entities = [
    AnimeRoom::class, CharacterRoom::class
                     ], version = 1, exportSchema = false)

@TypeConverters(Convert::class)

abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao

    companion object {
        private lateinit var dbInstance: AnimeDatabase
        fun getDatabase(context: Context): AnimeDatabase {
            synchronized(this) {
                // Initialisiere Datenbank
                if (!this::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimeDatabase::class.java,
                        "anime_database"
                    ).build()
                }
                return dbInstance
            }
        }
    }
}