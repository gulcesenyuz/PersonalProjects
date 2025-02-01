package com.example.personalprojects.models

data class MovieModel(
    val country: String,
    val genres: List<String>,
    val id: Int,
    val images: List<String>,
    val imdbRating: String,
    val poster: String,
    val title: String,
    val year: String
)