package com.example.randomjoke.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://evilinsult.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JokeApiService {

    @GET("generate_insult.php")
    suspend fun getJoke(@Query("lang") lang: String = "en",
    @Query("type") type: String = "json") : JokeNetwork

}

object JokeApi {
    val retrofitService: JokeApiService by lazy { retrofit.create(JokeApiService::class.java) }
}
