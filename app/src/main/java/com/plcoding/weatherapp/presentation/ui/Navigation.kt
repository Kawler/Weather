package com.plcoding.weatherapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.screens.AboutScreen
import com.plcoding.weatherapp.presentation.screens.GraphScreen
import com.plcoding.weatherapp.presentation.screens.ListScreen
import com.plcoding.weatherapp.presentation.screens.MainScreen

@Composable
fun Navigation(viewModel: WeatherViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.ListScreen.route){
            ListScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.GraphScreen.route){
            GraphScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.AboutScreen.route){
            AboutScreen(viewModel = viewModel, navController = navController)
        }
    }
}
