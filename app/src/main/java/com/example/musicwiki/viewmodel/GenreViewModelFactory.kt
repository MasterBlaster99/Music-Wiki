package com.example.musicwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.repository.GenresRepository

class GenreViewModelFactory(private val genresRepository: GenresRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GenresViewModel(genresRepository) as T
    }
}

