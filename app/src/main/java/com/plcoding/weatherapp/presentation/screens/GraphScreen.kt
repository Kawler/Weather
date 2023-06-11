package com.plcoding.weatherapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.AppBar
import com.plcoding.weatherapp.presentation.ui.DrawerBody
import com.plcoding.weatherapp.presentation.ui.DrawerHeader
import com.plcoding.weatherapp.presentation.ui.MenuItem
import com.plcoding.weatherapp.presentation.ui.Screen
import com.plcoding.weatherapp.presentation.ui.barChart.BarGraph
import com.plcoding.weatherapp.presentation.ui.barChart.BarType
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import kotlinx.coroutines.launch

@Composable
fun GraphScreen(viewModel: WeatherViewModel, navController: NavController){
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
        ) {
            Box(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(vertical = 30.dp)
                    .clip(shape = RoundedCornerShape(30))
                    .background(DeepBlue)
            ) {
                Text(
                    modifier = Modifier
                        .align(Center)
                        .padding(10.dp, 15.dp),
                    text = "Today's weather chart",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .background(DarkBlue)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val floatValue = mutableListOf<Float>()
                //List of time points
                val timeList = mutableListOf(0, 3, 6, 9, 12, 15, 18, 21)
                //Ugly but converts my data to the data needed to be passed to bar chart
                val dataConv = viewModel.state.weatherInfo!!.weatherDataPerDay.map { it.value.map { it.temperatureCelsius }}.get(0)
                val dataList = mutableListOf<Double>()
                //To lazy to do this better
                dataList.add(dataConv.get(0))
                dataList.add(dataConv.get(2))
                dataList.add(dataConv.get(5))
                dataList.add(dataConv.get(8))
                dataList.add(dataConv.get(11))
                dataList.add(dataConv.get(14))
                dataList.add(dataConv.get(17))
                dataList.add(dataConv.get(20))
                dataList.forEachIndexed { index, value ->

                    floatValue.add(index = index, element = value.toFloat()/dataList.sortedWith(compareBy { it }).last().toFloat())

                }
                BarGraph(
                    graphBarData = floatValue,
                    xAxisScaleData = timeList,
                    barData_ = dataList.map { it.toInt() },
                    height = 300.dp,
                    roundType = BarType.TOP_CURVED,
                    barWidth = 20.dp,
                    barColor = Color.Yellow,
                    barArrangement = Arrangement.SpaceEvenly
                )
            }
        }

    }
}