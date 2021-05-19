package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.MoviesRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MoviesRepository.getInstance(remoteDataSource)
    }
}