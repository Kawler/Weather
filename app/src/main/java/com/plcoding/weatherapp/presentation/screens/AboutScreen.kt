package com.plcoding.weatherapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.AppBar
import com.plcoding.weatherapp.presentation.ui.DrawerBody
import com.plcoding.weatherapp.presentation.ui.DrawerHeader
import com.plcoding.weatherapp.presentation.ui.MenuItem
import com.plcoding.weatherapp.presentation.ui.Screen
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(horizontal = 35.dp, vertical = 20.dp)
        ) {
            Text(
                text = "Всё об этом проекте",
                color = Color.White,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Данный  проект был написан в качестве примерного проекта для моей летней практики." +
                        " Таким образовм он содержит некоторое количество ошибок, ненужного или глупого кода" +
                        ", несколько неприятных моментов и так далее.",
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Сердце это проекта - получение данных с API, " +
                        "ресурсы для иконок и главная страница были написаны по гайду" +
                        ": https://www.youtube.com/watch?v=eAbKK7JNxCE",
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Для графика погоды был использован шаблон графа из: " +
                        "https://www.youtube.com/watch?v=PDaX-3tJjtw",
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Данные погоды получаются с: https://open-meteo.com/",
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Репозитория на github: https://github.com/Kawler/Weather",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}