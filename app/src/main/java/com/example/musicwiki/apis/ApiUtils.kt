package com.example.musicwiki.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {
    val BASE_URL = "https://ws.audioscrobbler.com/2.0/"
    fun instance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}