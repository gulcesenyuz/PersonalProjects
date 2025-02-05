package com.example.personalprojects.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalprojects.models.MovieDetailsModel
import com.example.personalprojects.models.MovieModel
import com.example.personalprojects.pagination.PaginationFactory
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()
    var state by mutableStateOf(MovieScreenState())
    //var movieId by mutableIntStateOf(0)

    private val pagination = PaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(
                isLoading = it
            )
        },
        onRequest = {nextPage ->
            movieRepository.getMovieList(nextPage)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = {items, newPage ->
            state = state.copy(
                movies = state.movies?.plus(items.data),
                page = newPage,
                endReached = state.page == 25
            )
        }
    )

    init {
        loadNextItems()
        /*
         viewModelScope.launch {
           val getMovieListResponse =  movieRepository.getMovieList(state.page)
            getMovieListResponse.body()?.data.let { state = state.copy(movies = it) }
        }
         */
    }

    fun getMovieDetailsById(id: Int) {
        viewModelScope.launch {
            try {
                val response = movieRepository.getDetailsById(id = id)
                if (response.isSuccessful) {
                    state = state.copy(
                        detailsData = response.body()!!
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    error = e.message
                )
            }
        }
    }

    fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }
}

data class MovieScreenState(
    val movies: List<MovieModel>? = emptyList(),
    val page: Int = 1,
    val detailsData: MovieDetailsModel = MovieDetailsModel(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)