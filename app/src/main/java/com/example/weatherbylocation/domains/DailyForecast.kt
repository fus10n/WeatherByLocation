package com.example.weatherbylocation.domains

import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("dt")
    val daytime: Long?,

    @SerializedName("sunrise")
    val sunrise: Long?,

    @SerializedName("sunset")
    val sunset: Long?,

    @SerializedName("moonrise")
    val moonrise: Long?,

    @SerializedName("moonset")
    val moonset: Long?,

    @SerializedName("pressure")
    val pressure: Int?,

    @SerializedName("humidity")
    val humidity: Int?,

    @SerializedName("dew_point")
    val dewPoint: Double?,

    @SerializedName("uvi")
    val uvIndex: Double?,

    @SerializedName("clouds")
    val clouds: Int?,

    @SerializedName("visibility")
    val visibility: Int?,

    @SerializedName("wind_speed")
    val windSpeed: Int?,

    @SerializedName("wind_deg")
    val windDeg: Int?,

    @SerializedName("wind_gust")
    val windGust: Double?,

    @SerializedName("weather")
    val weather: List<Weather>?,

    @SerializedName("precipitation")
    val precipitation: Double?,

    @SerializedName("pop")
    val pop: Double?,

    @SerializedName("temp")
    val temperature: Temperature?,

    @SerializedName("feels_like")
    val feelsLike: Temperature?,

    @SerializedName("rain")
    val rain: Double?
)
