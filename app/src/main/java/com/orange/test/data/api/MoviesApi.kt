package com.orange.test.data.api

import com.orange.test.data.model.Movie
import com.orange.test.data.model.MovieDetails
import com.orange.test.data.model.MoviesResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {
    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") key: String): Observable<MoviesResult>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") key: String): Observable<MoviesResult>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Observable<MovieDetails>
}