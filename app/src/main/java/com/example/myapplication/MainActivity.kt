package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.screens.MovieListScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val backStack = rememberNavBackStack(Screen.MovieList)

                NavDisplay(
                    backStack = backStack,
                    entryProvider = { key ->
                        when (key) {
                            is Screen.MovieList -> NavEntry(key) {
                                val viewModel: MovieListViewModel = hiltViewModel()
                                MovieListScreen(
                                    viewModel = viewModel,
                                    onMovieClick = { movieId ->
                                        backStack.add(Screen.MovieDetail(movieId))
                                    }
                                )
                            }
                            is Screen.MovieDetail -> NavEntry(key) {
                                Text("Detalle de la película con id: ${key.movieId}")
                            }
                            else -> throw IllegalStateException("Ruta desconocida")
                        }
                    }
                )
            }
        }
    }
}