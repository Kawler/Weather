package com.plcoding.weatherapp.presentation.ui

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object ListScreen : Screen("list_screen")
    object GraphScreen : Screen("graph_screen")
    object AboutScreen : Screen("about_screen")
}
