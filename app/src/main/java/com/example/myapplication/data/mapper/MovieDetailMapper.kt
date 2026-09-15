package com.example.myapplication.data.mapper

import com.example.myapplication.data.model.MovieDetail
import com.example.myapplication.data.remote.dto.MovieDetailDto


fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        runtime = runtime
    )
}