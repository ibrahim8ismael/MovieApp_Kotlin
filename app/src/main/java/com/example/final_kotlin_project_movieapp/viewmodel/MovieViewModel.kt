package com.example.final_kotlin_project_movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_kotlin_project_movieapp.data.MovieRepository
import com.example.final_kotlin_project_movieapp.model.Comment
import com.example.final_kotlin_project_movieapp.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class MovieViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movies.value = MovieRepository.getMovies()
        }
    }

    fun getMovie(id: String): Movie? {
        return MovieRepository.getMovie(id)
    }

    fun addComment(movieId: String, text: String) {
        viewModelScope.launch {
            val newComment = Comment(
                id = UUID.randomUUID().toString(),
                author = "User", // Hardcoded for simplicity as per requirement
                text = text
            )
            MovieRepository.addComment(movieId, newComment)
            // Reload movies to update state
            loadMovies()
        }
    }
}
