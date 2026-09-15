package com.example.myapplication.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
    @Serializable
    data object MovieList : Screen

    @Serializable
    data class MovieDetail(val movieId: Int) : Screen
}