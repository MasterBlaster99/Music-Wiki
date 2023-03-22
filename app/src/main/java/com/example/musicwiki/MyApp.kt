package com.example.musicwiki

import android.app.Application
import com.example.musicwiki.apis.ApiInterface
import com.example.musicwiki.apis.ApiUtils
import com.example.musicwiki.repository.GenresRepository

class MyApp: Application() {
    lateinit var repository: GenresRepository
    override fun onCreate() {
        super.onCreate()
        val apiInterface =  ApiUtils.instance().create(ApiInterface::class.java)
        repository = GenresRepository(apiInterface)
    }
}