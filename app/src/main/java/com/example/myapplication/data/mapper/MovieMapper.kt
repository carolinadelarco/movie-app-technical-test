package com.example.myapplication.data.mapper

import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieResponse
import com.example.myapplication.data.remote.dto.MovieDto
import com.example.myapplication.data.remote.dto.MovieResponseDto

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage
    )
}

fun MovieResponseDto.toMovieResponse(): MovieResponse {
    return MovieResponse(
        page = page,
        results = results.map { it.toMovie() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}