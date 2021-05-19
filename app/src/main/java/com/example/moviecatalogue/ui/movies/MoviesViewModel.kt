package com.example.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MoviesRepository


class MoviesViewModel(private val MovieRepository: MoviesRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = MovieRepository.getListMovie()
}