package com.plcoding.weatherapp.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.AppBar
import com.plcoding.weatherapp.presentation.ui.DrawerBody
import com.plcoding.weatherapp.presentation.ui.DrawerHeader
import com.plcoding.weatherapp.presentation.ui.MenuItem
import com.plcoding.weatherapp.presentation.ui.Screen
import kotlinx.coroutines.launch

@Composable
fun AboutScreen(viewModel: WeatherViewModel, navController: NavController){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar (
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = true,
        drawerContent = {
            DrawerHeader()
            DrawerBody(items = listOf(
                MenuItem(
                    id = "home",
                    title = "Home",
                    contentDescription = "Go to home screen",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    id = "list",
                    title = "List",
                    contentDescription = "Go to list screen",
                    icon = Icons.Default.List
                ),
                MenuItem(
                    id = "graph",
                    title = "Graph",
                    contentDescription = "Go to graph screen",
                    icon = Icons.Default.Check
                ),
                MenuItem(
                    id = "about",
                    title = "About",
                    contentDescription = "Go to about screen",
                    icon = Icons.Default.Home
                )
            ), onItemClick = {
                when(it.id){
                    "home" -> navController.navigate(Screen.MainScreen.route)
                    "graph" -> navController.navigate(Screen.GraphScreen.route)
                    "list" -> navController.navigate(Screen.ListScreen.route)
                    "about" -> navController.navigate(Screen.AboutScreen.route)
                }
            })
        }
    ) {
        Text(text = "About Screen")
    }
}