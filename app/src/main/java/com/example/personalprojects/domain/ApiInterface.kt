package com.example.personalprojects.domain

import com.example.personalprojects.models.MovieDetailsModel
import com.example.personalprojects.models.MovieListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET("movies?")
    suspend fun getMovies(
        @Query("page")page: Int
    ): Response<MovieListModel>

    @GET("movies/{movie_id}")
    suspend fun getDetailsById(
        @Path("movie_id")id: Int
    ):Response<MovieDetailsModel>
}