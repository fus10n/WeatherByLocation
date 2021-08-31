package com.example.weatherbylocation.domains

import com.google.gson.annotations.SerializedName

data class GeoLocation(
    @SerializedName("name")
    val name: String,

    @SerializedName("lat")
    val latitude: Double,

    @SerializedName("lon")
    val longitude: Double,

    @SerializedName("country")
    val country: String
)
