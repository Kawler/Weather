package com.plcoding.weatherapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.WeatherForecast
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.AppBar
import com.plcoding.weatherapp.presentation.ui.DrawerBody
import com.plcoding.weatherapp.presentation.ui.DrawerHeader
import com.plcoding.weatherapp.presentation.ui.MenuItem
import com.plcoding.weatherapp.presentation.ui.Screen
import com.plcoding.weatherapp.presentation.ui.WeatherCard
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: WeatherViewModel, navController: NavController){
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
                    icon = Icons.Default.Info
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

        Box(
            modifier = Modifier.fillMaxSize()
                .background(DarkBlue)
        ){
            if(viewModel.state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (viewModel.state.error != null){
                viewModel.state.error?.let {error ->
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                ) {
                    WeatherCard(
                        state = viewModel.state,
                        backgroundColor = DeepBlue
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                    ) {
                        //issue - For some unknown to me reasons displays even while loading
                        Text(
                            modifier = Modifier.align(CenterHorizontally),
                            text = "Today",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                    }
                }
            }
        }
    }
}