package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieResponse
import com.example.myapplication.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadPopularMovies()
    }

//    private fun loadPopularMovies() {
//        viewModelScope.launch {
//            _isLoading.value = true
//            _error.value = null
//            try {
//                val response = repository.getPopularMovies()
//                _movies.value = response.results
//                android.util.Log.d("MovieApp", "Películas recibidas: ${response.results.size}")
//            } catch (e: Exception) {
//                _error.value = "Error al cargar las películas: ${e.message}"
//                android.util.Log.e("MovieApp", "Error: ${e.message}", e)
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val moviesResult = repository.getPopularMovies().map { it.results }
            if (moviesResult.isSuccess) {
                _movies.value = moviesResult.getOrNull() ?: emptyList()
                android.util.Log.d("MovieApp", "Películas recibidas: ${moviesResult.getOrNull()?.size}")
            }
            if (moviesResult.isFailure) {
                _error.value = moviesResult.exceptionOrNull()?.message ?: "Error desconocido"
                android.util.Log.e("MovieApp", "Error: ${moviesResult.exceptionOrNull()?.message}", moviesResult.exceptionOrNull())
            }
            _isLoading.update { false }
        }
    }
}