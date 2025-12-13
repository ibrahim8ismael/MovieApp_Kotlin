package com.example.final_kotlin_project_movieapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.final_kotlin_project_movieapp.ui.screens.MovieDetailScreen
import com.example.final_kotlin_project_movieapp.ui.screens.MovieListScreen
import com.example.final_kotlin_project_movieapp.viewmodel.MovieViewModel

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val viewModel: MovieViewModel = viewModel() // Scope ViewModel to the NavHost or Activity, here simple

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MovieListScreen(
                viewModel = viewModel,
                onMovieClick = { movieId ->
                    navController.navigate("detail/$movieId")
                }
            )
        }
        composable("detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            if (movieId != null) {
                MovieDetailScreen(
                    movieId = movieId,
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
