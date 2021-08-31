package com.example.weatherbylocation.domains

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("lat")
    val lat: Double?,

    @SerializedName("lon")
    val lon: Double?,

    @SerializedName("timezone")
    val timezone: String?,

    @SerializedName("timezone_offset")
    val timezoneOffset: Int?,

    @SerializedName("current")
    val current: Forecast?,

    @SerializedName("minutely")
    val minutelyWeather: List<Forecast>?,

    @SerializedName("hourly")
    val hourlyWeather: List<Forecast>?,

    @SerializedName("daily")
    val dailyForecast: List<DailyForecast>?
)