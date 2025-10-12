package com.erickvazquezs.practicamodulo7.data

import com.erickvazquezs.practicamodulo7.data.remote.MoviesApi
import com.erickvazquezs.practicamodulo7.data.remote.model.MovieDto
import retrofit2.Retrofit

class MovieRepository(
    private val retrofit: Retrofit
) {
    private val moviesApi = retrofit.create(MoviesApi::class.java)

    suspend fun getMovies(): List<MovieDto> = moviesApi.getMovies()
    suspend fun getMovie(id: Int): MovieDto = moviesApi.getMovie(id)
}