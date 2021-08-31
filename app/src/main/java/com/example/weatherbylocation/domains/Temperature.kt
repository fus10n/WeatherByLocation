package com.example.weatherbylocation.domains

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("min")
    val minimum: Double?,

    @SerializedName("max")
    val maximum: Double?,

    @SerializedName("day")
    val day: Double?,

    @SerializedName("night")
    val night: Double?,

    @SerializedName("eve")
    val evening: Double?,

    @SerializedName("morn")
    val morning: Double?
)
