package com.example.final_kotlin_project_movieapp.data

import com.example.final_kotlin_project_movieapp.model.User
import java.util.UUID

class UserRepository {
    // In-memory storage for demo purposes
    // In a real app, you'd use Room database or remote API
    private val users = mutableListOf<User>()

    fun signup(email: String, password: String, name: String): Result<User> {
        // Check if user already exists
        if (users.any { it.email == email }) {
            return Result.failure(Exception("User with this email already exists"))
        }

        // Validate inputs
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            return Result.failure(Exception("All fields are required"))
        }

        if (!email.contains("@")) {
            return Result.failure(Exception("Invalid email format"))
        }

        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }

        // Create new user
        val newUser = User(
            id = UUID.randomUUID().toString(),
            email = email,
            password = password,
            name = name
        )
        users.add(newUser)
        return Result.success(newUser)
    }

    fun login(email: String, password: String): Result<User> {
        // Validate inputs
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Email and password are required"))
        }

        // Find user
        val user = users.find { it.email == email && it.password == password }
        return if (user != null) {
            Result.success(user)
        } else {
            Result.failure(Exception("Invalid email or password"))
        }
    }

    fun getCurrentUser(): User? {
        // In a real app, you'd check SharedPreferences or a session token
        return users.firstOrNull()
    }
}
