package com.plcoding.weatherapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*TODO*/
@Entity
data class WeatherEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "latitude") val latitude : Double,
    @ColumnInfo(name = "longitude") val longitude : Double,
    @ColumnInfo(name = "time") val time : List<String>,
    @ColumnInfo(name = "temperatures") val temperatures: List<Double>,
    @ColumnInfo(name = "weatherCodes")  val weatherCodes: List<Int>,
    @ColumnInfo(name = "pressures")  val pressures: List<Double>,
    @ColumnInfo(name = "windSpeeds")  val windSpeeds: List<Double>,
    @ColumnInfo(name = "humidities")  val humidities: List<Double>
)
