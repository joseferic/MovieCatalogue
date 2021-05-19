package com.example.moviecatalogue.ui.tvshow

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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(Repository)
    }

    @Test
    fun getTVShows() {
        val dummyTVShows = DataDummy.dataListTVShow()
        val tvshows = MutableLiveData<List<MovieEntity>>()
        tvshows.value = dummyTVShows

        Mockito.`when`(Repository.getListTVShow()).thenReturn(tvshows)
        val tvshowEntities = viewModel.getTVShow().value
        Mockito.verify(Repository).getListTVShow()
        assertNotNull(tvshowEntities)

        viewModel.getTVShow().observeForever(observer)
        Mockito.verify(observer).onChanged(tvshows.value)
    }

}