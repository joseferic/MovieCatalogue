package com.example.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.MovieResponse
import com.example.moviecatalogue.data.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TVDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TVShowResponse
import com.example.moviecatalogue.data.source.remote.response.TVShowResultsItem
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource().apply { instance = this }
                }
    }

    fun getMovieList(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val client = ApiConfig.getApiService().getMovie()
            client.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        Log.d(this@RemoteDataSource.toString(), "Get Movies Success")
                        response.body()?.results?.let { callback.onAllMoviesReceived(it) }
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("Tag Fail get list", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e("Tag Fail get response", "onFailure: ${t.message.toString()}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResultsItem>)
    }

    fun getTVShowList(callback: LoadTVShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val client = ApiConfig.getApiService().getTVShow()
            client.enqueue(object : Callback<TVShowResponse> {
                override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                    if (response.isSuccessful) {
                        Log.d(this@RemoteDataSource.toString(), "Get TV Shows Success")
                        response.body()?.results?.let { callback.onAllTVShowReceived(it) }
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("Tag Fail get list", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                    Log.e("Tag Fail get response", "onFailure: ${t.message.toString()}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTVShowCallback {
        fun onAllTVShowReceived(tvShowResponse: List<TVShowResultsItem>)
    }

    fun getMovieDetail(callback: LoadMovieDetailCallback, movieId: Int) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val client = ApiConfig.getApiService().getDetailMovie(movieId)
            client.enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                    if (response.isSuccessful) {
                        Log.d(this@RemoteDataSource.toString(), "Detail Movie Success")
                        response.body()?.let { callback.onMovieDetailsReceived(it) }
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("Tag Fail getmoviedetail", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Log.e("Tag Fail get response", "onFailure: ${t.message.toString()}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailsReceived(detailMovieresponse: MovieDetailResponse)
    }

    fun getTVShowDetail(callback: LoadTVShowDetailCallback, tvId: Int) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            val client = ApiConfig.getApiService().getDetailTVShow(tvId)
            client.enqueue(object : Callback<TVDetailResponse> {
                override fun onResponse(call: Call<TVDetailResponse>, response: Response<TVDetailResponse>) {
                    Log.d("Response = ", response.toString())
                    if (response.isSuccessful) {
                        Log.d(this@RemoteDataSource.toString(), "Detail TV Show Success")
                        response.body()?.let { callback.onTVShowDetailReceived(it) }
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("Tag Fail getTVdetail ", "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TVDetailResponse>, t: Throwable) {
                    Log.e("Tag Fail get response", "onFailure: ${t.message.toString()}")
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTVShowDetailCallback {
        fun onTVShowDetailReceived(detailTVShowresponse: TVDetailResponse)
    }
}


