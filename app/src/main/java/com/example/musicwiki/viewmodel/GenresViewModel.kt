package com.example.musicwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.model.GenreName
import com.example.musicwiki.repository.GenresRepository
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenresViewModel(private val genresRepository: GenresRepository) : ViewModel() {

    val genres: LiveData<JsonArray>
        get() = genresRepository.genres

    val genresInfo: LiveData<JsonObject>
        get() = genresRepository.genresInfo

    val album: LiveData<JsonArray>
        get() = genresRepository.album

    val artists: LiveData<JsonArray>
        get() = genresRepository.artists

    val artistInfo: LiveData<JsonObject>
        get() = genresRepository.artistInfo

    fun getGenreInfo(genreName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            genresRepository.getGenreInfo("https://ws.audioscrobbler.com/2.0/?method=tag.getinfo&tag=$genreName&api_key=ae06783a7d927f4b558c5f7d4041d270&format=json")
        }
    }

    fun getAllGenres(url: String) {
        viewModelScope.launch(Dispatchers.IO)
        {
            genresRepository.getGenreNames(url)
        }
    }

    fun getAlbum(genreName: String) {
        viewModelScope.launch(Dispatchers.IO)
        {
            genresRepository.getAlbum("https://ws.audioscrobbler.com/2.0/?method=tag.gettopalbums&tag=$genreName&api_key=ae06783a7d927f4b558c5f7d4041d270&format=json")
        }
    }

    fun getArtists(genreName: String) {
        viewModelScope.launch(Dispatchers.IO)
        {
            genresRepository.getArtists("https://ws.audioscrobbler.com/2.0/?method=tag.gettopartists&tag=$genreName&api_key=ae06783a7d927f4b558c5f7d4041d270&format=json")
        }
    }

    fun getArtistInfo(artistName:String){
        viewModelScope.launch(Dispatchers.IO) {
            genresRepository.getArtistInfo("https://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=$artistName&api_key=ae06783a7d927f4b558c5f7d4041d270&format=json")
        }
    }

}