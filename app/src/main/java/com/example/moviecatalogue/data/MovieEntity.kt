package com.example.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
        var movieId: Int,
        var title: String,
        var description: String,
        var tvShow: Boolean = false,
        var imagePath: String,
        var rating: String
) : Parcelable
