package com.example.moviecatalogue.ui.detail


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MoviesRepository
import com.example.moviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.dataListMovie()[0]
    private val movieId = dummyMovie.movieId
    private val dummyTVShow = DataDummy.dataListTVShow()[0]
    private val tvshowId = dummyTVShow.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repository: MoviesRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvshowObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(Repository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(Repository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie(movieId).value as MovieEntity
        verify(Repository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.rating, movieEntity.rating)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)
        assertEquals(dummyMovie.title, movieEntity.title)

        viewModel.getDetailMovie(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)

    }

    @Test
    fun getDetailTVShow() {
        val tvshow = MutableLiveData<MovieEntity>()
        tvshow.value = dummyTVShow

        `when`(Repository.getTVShowDetail(tvshowId)).thenReturn(tvshow)
        val tvshowEntity = viewModel.getDetailTVShow(tvshowId).value as MovieEntity
        verify(Repository).getTVShowDetail(tvshowId)
        assertNotNull(tvshowEntity)
        assertEquals(dummyTVShow.movieId, tvshowEntity.movieId)
        assertEquals(dummyTVShow.rating, tvshowEntity.rating)
        assertEquals(dummyTVShow.description, tvshowEntity.description)
        assertEquals(dummyTVShow.imagePath, tvshowEntity.imagePath)
        assertEquals(dummyTVShow.title, tvshowEntity.title)

        viewModel.getDetailTVShow(tvshowId).observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummyTVShow)
    }
}