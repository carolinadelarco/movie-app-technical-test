package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toMovieResponse
import com.example.myapplication.data.model.MovieResponse
import com.example.myapplication.data.remote.KtorClient
import com.example.myapplication.data.remote.dto.MovieResponseDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieRepository {

    private val apiKey = "387c26478e8888901084cfe1756a1974"

    suspend fun getPopularMovies(): MovieResponse {
        val responseDto: MovieResponseDto = KtorClient.httpClient
            .get("https://api.themoviedb.org/3/movie/popular") {
                parameter("api_key", apiKey)
                parameter("language", "es-ES")
            }
            .body()

        return responseDto.toMovieResponse()
    }
}