package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.MovieEntity

interface MoviesDataSource {
    fun getListMovie(): LiveData<List<MovieEntity>>
    fun getListTVShow(): LiveData<List<MovieEntity>>
    fun getMovieDetail(movie_id: Int): LiveData<MovieEntity>
    fun getTVShowDetail(tv_id: Int): LiveData<MovieEntity>
}