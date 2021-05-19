package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.databinding.ContentDetailBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var contentDetailBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentDetailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //data dari intent
        val dataIntent = intent.getParcelableExtra(EXTRA_MOVIES) as MovieEntity
        Log.d("Data Detail =", dataIntent.movieId.toString() + "tvshow = " + dataIntent.tvShow)

        //View Model
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


        if (dataIntent.tvShow == false) {
            viewModel.getDetailMovie(dataIntent.movieId).observe(this, { movie ->
                contentDetailBinding.textTitle.text = movie.title
                contentDetailBinding.textDescription.text = movie.description
                contentDetailBinding.textRating.text = movie.rating
                Glide.with(this)
                        .load(movie.imagePath)
                        .dontAnimate()
                        .apply(RequestOptions())
                        .into(contentDetailBinding.imagePoster)
                Log.d("Data ditampilkan", "berhasil")
            })
        }
        if (dataIntent.tvShow == true) {
            viewModel.getDetailTVShow(dataIntent.movieId).observe(this, { tvshow ->
                contentDetailBinding.textTitle.text = tvshow.title
                contentDetailBinding.textDescription.text = tvshow.description
                contentDetailBinding.textRating.text = tvshow.rating
                Glide.with(this)
                        .load(tvshow.imagePath)
                        .dontAnimate()
                        .apply(RequestOptions())
                        .into(contentDetailBinding.imagePoster)
                Log.d("Data ditampilkan", "berhasil")
            })
        }
    }
}

