package com.example.musicwiki.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.apis.ApiInterface
import com.example.musicwiki.model.GenreName
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.http.Url
import java.lang.Exception

class GenresRepository(private val apiInterface: ApiInterface) {

    private val genresLiveData = MutableLiveData<JsonArray>()
    val genres: LiveData<JsonArray>
    get() = genresLiveData

    private val genresInfoLiveData = MutableLiveData<JsonObject>()
    val genresInfo: LiveData<JsonObject>
        get() = genresInfoLiveData

    private val albumLiveData = MutableLiveData<JsonArray>()
    val album: LiveData<JsonArray>
        get() = albumLiveData

    private val artistLiveData = MutableLiveData<JsonArray>()
    val artists: LiveData<JsonArray>
        get() = artistLiveData

    private val artistInfoLiveData = MutableLiveData<JsonObject>()
    val artistInfo: LiveData<JsonObject>
        get() = artistInfoLiveData

    suspend fun getGenreNames(url: String): Boolean {
        try {
            val result = apiInterface.getGenreNames(url)
            if (result != null && result.isSuccessful) {
                Log.d(TAG, "getGenreNames: ${result.body()}")
                genresLiveData.postValue(result.body()?.toptags?.get("tag")?.asJsonArray)
                return true
            }
            else {
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }
    suspend fun getGenreInfo(url: String){
        try {
            val result = apiInterface.getGenreInfo(url)
            if (result != null && result.isSuccessful) {
                Log.d(TAG, "getGenresInfo: ${result.body()}")
                genresInfoLiveData.postValue(result.body()?.tag)
            }
        } catch (e: Exception) {
        }
    }
    suspend fun getAlbum(url: String){
        try {
            val result = apiInterface.getAlbum(url)
            if (result != null && result.isSuccessful) {
                Log.d(TAG, "getAlbum: ${result.body()}")
                albumLiveData.postValue(result.body()?.albums?.get("album")?.asJsonArray)
            }
        } catch (e: Exception) {
        }
    }
    suspend fun getArtists(url: String){
        try {
            val result = apiInterface.getArtists(url)
            if (result != null && result.isSuccessful) {
                Log.d(TAG, "getArtists: ${result.body()}")
                artistLiveData.postValue(result.body()?.topartists?.get("artist")?.asJsonArray)
            }
        } catch (e: Exception) {
        }
    }
    suspend fun getArtistInfo(url: String) {
        try {
            val result = apiInterface.getArtistInfo(url)
            if (result != null && result.isSuccessful) {
                Log.d(TAG, "getArtistInfo: ${result.body()}")
                artistInfoLiveData.postValue(result.body()?.artist)
            }
        } catch (e: Exception) {
        }
    }

}