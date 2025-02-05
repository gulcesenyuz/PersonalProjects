package com.example.personalprojects.models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val country: String,
    val genres: List<String>,
    val id: Int,
    val images: List<String>,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    val poster: String,
    val title: String,
    val year: String
)