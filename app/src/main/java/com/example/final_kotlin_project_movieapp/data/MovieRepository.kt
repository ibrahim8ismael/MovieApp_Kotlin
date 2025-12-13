package com.example.final_kotlin_project_movieapp.data

import com.example.final_kotlin_project_movieapp.model.Comment
import com.example.final_kotlin_project_movieapp.model.Movie

object MovieRepository {
    private val movies = mutableListOf(
        Movie(
            id = "1",
            title = "Inception",
            description = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            rating = 8.8f,
            imageUrl = "https://image.tmdb.org/t/p/w500/9gk7admal4zl2mO4CyOATrNYt3I.jpg",
            comments = mutableListOf(
                Comment("1", "User1", "Mind blowing movie!"),
                Comment("2", "User2", "Constructive confusing but great.")
            )
        ),
        Movie(
            id = "2",
            title = "Interstellar",
            description = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
            rating = 8.6f,
            imageUrl = "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
            comments = mutableListOf(
                Comment("3", "User3", "Emotional rollercoaster."),
                Comment("4", "User4", "Hans Zimmer is a genius.")
            )
        ),
        Movie(
            id = "3",
            title = "The Dark Knight",
            description = "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            rating = 9.0f,
            imageUrl = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
            comments = mutableListOf(
                Comment("5", "User5", "The best superhero movie ever.")
            )
        ),
        Movie(
            id = "4",
            title = "Avatar",
            description = "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
            rating = 7.8f,
            imageUrl = "https://image.tmdb.org/t/p/w500/kyeqWdyUXW608qlYkRqosgbbJyK.jpg",
            comments = mutableListOf()
        ),
        Movie(
            id = "5",
            title = "The Avengers",
            description = "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.",
            rating = 8.0f,
            imageUrl = "https://image.tmdb.org/t/p/w500/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
            comments = mutableListOf(
                 Comment("6", "User6", "Hulk smash!")
            )
        )
    )

    fun getMovies(): List<Movie> = movies

    fun getMovie(id: String): Movie? = movies.find { it.id == id }

    fun addComment(movieId: String, comment: Comment) {
        val movie = getMovie(movieId)
        movie?.comments?.add(comment)
    }
}
