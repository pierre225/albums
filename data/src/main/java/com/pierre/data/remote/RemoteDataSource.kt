package com.pierre.data.remote

import com.pierre.data.remote.service.RemoteService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// todo implementer une classe data source abstraite entre emote et room
@Singleton
class RemoteDataSource {

    private val client by lazy {
        OkHttpClient.Builder()
            .apply {  }
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    private val retrofit by lazy {
            Retrofit
                .Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val service by lazy {
        retrofit.create(RemoteService::class.java)
    }

    suspend fun remoteSongs() = service.songs()

    companion object {
        private const val BASE_URL = "https://static.leboncoin.fr"
    }

}