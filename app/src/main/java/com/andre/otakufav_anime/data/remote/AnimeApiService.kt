package com.andre.otakufav_anime.data.remote

import com.example.animeapp.data.model.Anime
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://andreklein.mlm4u.eu/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface AnimeAPIService {

    @GET("animeinfo/version.json")
    suspend fun getVersion(): VersionResponse

    @GET("animeinfo/animeinfo.json")
    suspend fun getAnimes(): List<AnimeApiResponse>

}

object AnimeApi {
    val retrofitService: AnimeAPIService by lazy {
        retrofit.create(AnimeAPIService::class.java)
    }
}

data class VersionResponse(
    var version: Double
)
