package com.orange.test.data.model

data class MoviesResult(
    val results: List<Movie> = emptyList(),
    val page: Int,
    val total_pages: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String
)

data class MovieDetails(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val status: String
)

