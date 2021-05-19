package com.example.moviecatalogue.api

import com.example.moviecatalogue.data.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TVDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //https://api.themoviedb.org/3/movie/popular?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US&page=1
    //https://api.themoviedb.org/3/
    @GET("movie/popular?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US&page=1")
    fun getMovie(
    ): Call<MovieResponse>

    //https://api.themoviedb.org/3/tv/popular?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US&page=1
    @GET("tv/popular?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US&page=1")
    fun getTVShow(
    ): Call<TVShowResponse>


    // https://api.themoviedb.org/3/movie/567189?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US
    @GET("movie/{movie_id}?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US")
    fun getDetailMovie(
            @Path("movie_id") movie_id: Int
    ): Call<MovieDetailResponse>

    //https://api.themoviedb.org/3/tv/88396?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US
    @GET("tv/{tv_id}?api_key=088e5435bc1a50a5df6384cb4758456a&language=en-US")
    fun getDetailTVShow(
            @Path("tv_id") tv_id: Int
    ): Call<TVDetailResponse>

}