package com.plcoding.weatherapp.presentation.ui

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object ListScreen : Screen("list_screen")
    object GraphScreen : Screen("graph_screen")
    object AboutScreen : Screen("about_screen")
    object DefaultListScreen : Screen ("default_list_screen")
    object WorkDaysListScreen : Screen ("work_days_screen")
    object WeekendsListScreen : Screen ("weekends_list_screen")
}
