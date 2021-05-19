package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MoviesRepository


class DetailViewModel(private val DetailRepository: MoviesRepository) : ViewModel() {
    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = DetailRepository.getMovieDetail(movieId)
    fun getDetailTVShow(tvId: Int): LiveData<MovieEntity> = DetailRepository.getTVShowDetail(tvId)
}