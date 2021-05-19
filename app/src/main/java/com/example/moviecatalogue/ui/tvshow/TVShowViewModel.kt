package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MoviesRepository


class TVShowViewModel(private val TVShowRepository: MoviesRepository) : ViewModel() {
    fun getTVShow(): LiveData<List<MovieEntity>> = TVShowRepository.getListTVShow()
}