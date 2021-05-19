package com.example.moviecatalogue.data.source


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.MovieResultsItem
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TVDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TVShowResultsItem

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) : MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MoviesRepository =
                instance ?: synchronized(this) {
                    instance ?: MoviesRepository(remoteData).apply { instance = this }
                }
    }


    override fun getListMovie(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovieList(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResultsItem>) {
                val listMovie = ArrayList<MovieEntity>()
                if (movieResponse != null) {
                    for (response in movieResponse) {
                        val movie = MovieEntity(
                                response.id,
                                response.title,
                                response.overview,
                                false,
                                "https://image.tmdb.org/t/p/w500/" + response.posterPath,
                                response.voteAverage.toString()
                        )
                        listMovie.add(movie)
                    }
                }
                movieResult.postValue(listMovie)
            }
        })
        Log.d("Data movieResult",movieResult.value.toString())
        return movieResult
    }

    override fun getListTVShow(): LiveData<List<MovieEntity>> {
        val tvshowResult = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getTVShowList(object : RemoteDataSource.LoadTVShowCallback {
            override fun onAllTVShowReceived(tvShowResponse: List<TVShowResultsItem>) {
                val listTVShow = ArrayList<MovieEntity>()
                if (tvShowResponse != null) {
                    for (response in tvShowResponse) {
                        val tvshow = MovieEntity(
                                response.id,
                                response.name,
                                response.overview,
                                true,
                                "https://image.tmdb.org/t/p/w500/" + response.posterPath,
                                response.voteAverage.toString()
                        )
                        listTVShow.add(tvshow)
                    }
                }
                tvshowResult.postValue(listTVShow)
            }
        })
        return tvshowResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        val detailmovieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieDetail(object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onMovieDetailsReceived(detailMovieresponse: MovieDetailResponse) {
                val movie = MovieEntity(
                        detailMovieresponse.id,
                        detailMovieresponse.title,
                        detailMovieresponse.overview,
                        false,
                        "https://image.tmdb.org/t/p/w500/" + detailMovieresponse.posterPath,
                        detailMovieresponse.voteAverage.toString()
                )
                detailmovieResult.postValue(movie)
            }
        }, movieId)
        return detailmovieResult
    }

    override fun getTVShowDetail(tvId: Int): LiveData<MovieEntity> {
        val detailtvshowresult = MutableLiveData<MovieEntity>()
        remoteDataSource.getTVShowDetail(object : RemoteDataSource.LoadTVShowDetailCallback {
            override fun onTVShowDetailReceived(detailTVShowresponse: TVDetailResponse) {
                val tvshow = MovieEntity(
                        detailTVShowresponse.id,
                        detailTVShowresponse.name,
                        detailTVShowresponse.overview,
                        true,
                        "https://image.tmdb.org/t/p/w500/" + detailTVShowresponse.posterPath,
                        detailTVShowresponse.voteAverage.toString()
                )
                detailtvshowresult.postValue(tvshow)
            }
        }, tvId)
        return detailtvshowresult
    }

}