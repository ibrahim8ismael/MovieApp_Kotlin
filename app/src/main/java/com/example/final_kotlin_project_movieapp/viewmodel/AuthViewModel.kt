package com.example.final_kotlin_project_movieapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_kotlin_project_movieapp.data.UserRepository
import com.example.final_kotlin_project_movieapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val currentUser: User? = null,
    val error: String? = null
)

class AuthViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        val user = userRepository.getCurrentUser()
        _authState.value = _authState.value.copy(
            isAuthenticated = user != null,
            currentUser = user
        )
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            val result = userRepository.login(email, password)
            
            result.fold(
                onSuccess = { user ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        isAuthenticated = true,
                        currentUser = user,
                        error = null
                    )
                },
                onFailure = { exception ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        isAuthenticated = false,
                        error = exception.message
                    )
                }
            )
        }
    }

    fun signup(email: String, password: String, name: String) {
        viewModelScope.launch {
            _authState.value = _authState.value.copy(isLoading = true, error = null)
            
            val result = userRepository.signup(email, password, name)
            
            result.fold(
                onSuccess = { user ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        isAuthenticated = true,
                        currentUser = user,
                        error = null
                    )
                },
                onFailure = { exception ->
                    _authState.value = _authState.value.copy(
                        isLoading = false,
                        isAuthenticated = false,
                        error = exception.message
                    )
                }
            )
        }
    }

    fun logout() {
        _authState.value = AuthState()
    }

    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
}
