package com.example.final_kotlin_project_movieapp.model

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val rating: Float,
    val imageUrl: String,
    val comments: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val id: String,
    val author: String,
    val text: String
)
