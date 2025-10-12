package com.erickvazquezs.practicamodulo7.data.remote

import com.erickvazquezs.practicamodulo7.data.remote.model.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("/movies")
    suspend fun getMovies(): List<MovieDto>

    @GET("/movies/{id}")
    suspend fun getMovie(
        @Path("id") id: Int
    ): MovieDto
}