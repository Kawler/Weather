package com.plcoding.weatherapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.WeatherForecast
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.Screen
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import java.time.LocalDate

@Composable
fun DefaultDisplay(viewModel: WeatherViewModel, currentDate: LocalDate, y: Int, navController: NavController){
    val listState = rememberLazyListState()
    val fabVisibility by derivedStateOf {
        listState.firstVisibleItemIndex == 0
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        ButtonsRow(
            navController = navController,
            isVisible = fabVisibility,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(DeepBlue)
        )
        LazyColumn(
            modifier = Modifier
                .background(DarkBlue),
            state = listState
        ) {
            items(y){ index ->
                Row(
                    Modifier.padding(15.dp, 0.dp)
                ) {
                    Text(
                        text = "${currentDate.plusDays(index.toLong())}",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = currentDate.plusDays(index.toLong()).dayOfWeek.name,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                WeatherForecast(state = viewModel.state, index = index)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ButtonsRow(navController: NavController, isVisible: Boolean, modifier: Modifier){
    val density = LocalDensity.current
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = slideInVertically {
            with(density) { 40.dp.roundToPx() }
        } + fadeIn(),
        exit = fadeOut(
            animationSpec = keyframes {
                this.durationMillis = 60
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue),
                modifier = Modifier,
                onClick = {
                    navController.navigate(Screen.DefaultListScreen.route)
                }) {
                Text(
                    text = "Default",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue),
                modifier = Modifier
                ,
                onClick = {
                    navController.navigate(Screen.WorkDaysListScreen.route)
                }) {
                Text(
                    text = "Work days",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = DeepBlue),
                modifier = Modifier
                ,
                onClick = {
                    navController.navigate(Screen.WeekendsListScreen.route)
                }) {
                Text(
                    text = "Weekends",
                    color = Color.White
                )
            }
        }
    }
}