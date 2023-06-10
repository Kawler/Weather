package com.plcoding.weatherapp.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.screens.DefaultDisplay
import com.plcoding.weatherapp.presentation.screens.WeekendsDisplay
import com.plcoding.weatherapp.presentation.screens.WorkDaysDisplay
import com.plcoding.weatherapp.presentation.ui.Screen
import java.time.LocalDate

@Composable
fun ListScreenNavigation(viewModel: WeatherViewModel, currentDate: LocalDate, y: Int){
    val navListController = rememberNavController()
    NavHost(navController = navListController, startDestination = Screen.DefaultListScreen.route){
        composable(route = Screen.DefaultListScreen.route){
            DefaultDisplay(viewModel = viewModel, currentDate = currentDate, y = y, navController = navListController)
        }
        composable(route = Screen.WorkDaysListScreen.route){
            WorkDaysDisplay(viewModel = viewModel, currentDate = currentDate, y = y, navController = navListController)
        }
        composable(route = Screen.WeekendsListScreen.route){
            WeekendsDisplay(viewModel = viewModel, currentDate = currentDate, y = y, navController = navListController)
        }
    }
}