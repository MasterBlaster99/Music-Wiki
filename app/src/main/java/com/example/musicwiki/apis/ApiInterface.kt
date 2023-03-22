package com.example.musicwiki.apis

import com.example.musicwiki.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Url

interface ApiInterface {
    @GET
    suspend fun getGenreNames(@Url url: String): Response<GenreName>

    @GET
    suspend fun getGenreInfo(@Url url: String): Response<Genre>

    @GET
    suspend fun getAlbum(@Url url:String): Response<Album>

    @GET
    suspend fun getArtists(@Url url:String): Response<Artists>

    @GET
    suspend fun getArtistInfo(@Url url: String): Response<ArtistInfo>
}