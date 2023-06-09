package com.plcoding.weatherapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.HourlyWeatherDisplay
import com.plcoding.weatherapp.presentation.WeatherForecast
import com.plcoding.weatherapp.presentation.WeatherState
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.AppBar
import com.plcoding.weatherapp.presentation.ui.DrawerBody
import com.plcoding.weatherapp.presentation.ui.DrawerHeader
import com.plcoding.weatherapp.presentation.ui.MenuItem
import com.plcoding.weatherapp.presentation.ui.Screen
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import kotlinx.coroutines.launch
import java.lang.Exception

@Composable
fun ListScreen(viewModel: WeatherViewModel, navController: NavController){
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Days")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Days")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Days")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .background(DarkBlue)
            ) {

            }
        }
    }
}