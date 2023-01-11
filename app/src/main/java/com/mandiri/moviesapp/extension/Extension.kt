package com.mandiri.moviesapp.extension

import android.widget.Toast
import com.mandiri.moviesapp.MoviesApplication

fun showToast(message: String) {
    Toast.makeText(MoviesApplication.instance, message, Toast.LENGTH_LONG).show()
}

fun getMoviesUrl(path: String): String =
    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2$path"

fun getYoutubeThumbnailUrl(id: String): String =
    "https://img.youtube.com/vi/$id/0.jpg"