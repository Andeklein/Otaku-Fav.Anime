package com.andre.otakufav_anime.data.remote

import androidx.lifecycle.LiveData
import com.example.animeapp.data.model.Anime
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object AnimeAPI {
    private const val BASE_URL = "https://0.0.0.0:8080/"
    private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client = OkHttpClient.Builder().addInterceptor(logger).build()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val converter = MoshiConverterFactory.create(moshi)

    val service: AnimeApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(converter)
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(AnimeApiService::class.java)
    }
}

interface AnimeApiService {
    @GET("anime")
    suspend fun getAnime(): List<Anime>
}
