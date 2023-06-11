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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.WeatherForecast
import com.plcoding.weatherapp.presentation.WeatherViewModel
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import java.time.LocalDate

@Composable
fun WeekendsDisplay(viewModel: WeatherViewModel, currentDate: LocalDate, y: Int, navController: NavController){
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
                .background(DarkBlue)
                .fillMaxHeight()
        ) {
            items(y){ index ->
                //Checks name of the day
                if (currentDate.plusDays(index.toLong()).dayOfWeek.name == "SATURDAY" || currentDate.plusDays(index.toLong()).dayOfWeek.name == "SUNDAY"){
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

}