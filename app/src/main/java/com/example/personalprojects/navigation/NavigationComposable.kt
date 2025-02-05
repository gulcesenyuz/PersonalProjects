package com.example.personalprojects.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.personalprojects.ui.screens.HomeScreen
import com.example.personalprojects.ui.screens.MovieDetailScreen
import com.example.personalprojects.ui.screens.SplashScreen

@Composable
fun NavigationComposable() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash screen") {
        composable("Splash screen") {
            SplashScreen(navController = navController)
        }
        composable("Home screen") {
            HomeScreen(navController = navController)
        }
        composable("Movie details screen/{movieId}",
            arguments = listOf(
                navArgument(
                    name = "movieId"
                ) {
                    type = NavType.IntType
                }
            )
        ) {id->
            id.arguments?.getInt("id")?.let { movieId->
                MovieDetailScreen(id =movieId)
            }

        }
    }
}

