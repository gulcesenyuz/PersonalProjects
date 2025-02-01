package com.example.personalprojects.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalprojects.models.MovieModel
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()
    var state by mutableStateOf(MovieScreenState())

    init {
        viewModelScope.launch {
           val getMovieListResponse =  movieRepository.getMovieList(state.page)
            getMovieListResponse.body()?.data.let { state = state.copy(movies = it) }
        }
    }

}

data class MovieScreenState(
    val movies: List<MovieModel>? = emptyList(),
    val page: Int = 1
)