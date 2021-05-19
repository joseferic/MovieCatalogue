package com.example.moviecatalogue.ui.movies


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
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(Repository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.dataListMovie()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(Repository.getListMovie()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(Repository).getListMovie()
        assertNotNull(movieEntities)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movies.value)
    }
}