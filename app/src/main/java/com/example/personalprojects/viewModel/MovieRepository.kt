package com.example.personalprojects.viewModel

import com.example.personalprojects.models.MovieDetailsModel
import com.example.personalprojects.models.MovieListModel
import com.example.personalprojects.utils.RetrofitInstance
import retrofit2.Response

class MovieRepository {
    suspend fun getMovieList(page:Int): Response<MovieListModel> {
        return RetrofitInstance.api.getMovies(page)
    }
    suspend fun getDetailsById(id:Int): Response<MovieDetailsModel>{
        return RetrofitInstance.api.getDetailsById(id = id)
    }
}