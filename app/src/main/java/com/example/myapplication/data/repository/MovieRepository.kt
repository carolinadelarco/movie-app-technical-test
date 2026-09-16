package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toMovieResponse
import com.example.myapplication.data.model.MovieResponse
import com.example.myapplication.data.remote.KtorClient
import com.example.myapplication.data.remote.dto.MovieResponseDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import com.example.myapplication.BuildConfig
import javax.inject.Inject



class MovieRepository @Inject constructor()  {

    private val apiKey = BuildConfig.TMDB_API_KEY

    suspend fun getPopularMovies(): Result<MovieResponse> = runCatching {
        KtorClient.httpClient
            .get("https://api.themoviedb.org/3/movie/popular") {
                parameter("api_key", apiKey)
                parameter("language", "es-ES")
            }.body<MovieResponseDto>()
    }.map { it.toMovieResponse() }
}