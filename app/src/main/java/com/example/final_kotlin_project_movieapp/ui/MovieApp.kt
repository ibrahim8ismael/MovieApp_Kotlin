package com.example.final_kotlin_project_movieapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.final_kotlin_project_movieapp.ui.screens.LoginScreen
import com.example.final_kotlin_project_movieapp.ui.screens.MovieDetailScreen
import com.example.final_kotlin_project_movieapp.ui.screens.MovieListScreen
import com.example.final_kotlin_project_movieapp.ui.screens.SignupScreen
import com.example.final_kotlin_project_movieapp.viewmodel.AuthViewModel
import com.example.final_kotlin_project_movieapp.viewmodel.MovieViewModel

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()
    
    val authState by authViewModel.authState.collectAsState()

    // Navigate to home if already authenticated
    LaunchedEffect(authState.isAuthenticated) {
        if (authState.isAuthenticated) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController, 
        startDestination = if (authState.isAuthenticated) "home" else "login"
    ) {
        // Login Screen
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate("signup")
                },
                onLogin = { email, password ->
                    authViewModel.login(email, password)
                },
                isLoading = authState.isLoading,
                error = authState.error
            )
        }

        // Signup Screen
        composable("signup") {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onSignup = { email, password, name ->
                    authViewModel.signup(email, password, name)
                },
                isLoading = authState.isLoading,
                error = authState.error
            )
        }

        // Movie List Screen (Home)
        composable("home") {
            MovieListScreen(
                viewModel = movieViewModel,
                onMovieClick = { movieId ->
                    navController.navigate("detail/$movieId")
                }
            )
        }

        // Movie Detail Screen
        composable("detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            if (movieId != null) {
                MovieDetailScreen(
                    movieId = movieId,
                    viewModel = movieViewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
