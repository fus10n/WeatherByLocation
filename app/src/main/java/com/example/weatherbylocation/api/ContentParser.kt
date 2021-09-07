package com.example.weatherbylocation.api

import com.example.weatherbylocation.domains.ForecastResponse
import com.example.weatherbylocation.domains.GeoLocation
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author TH
 */
object ContentParser {

    private val gson = GsonBuilder().create()

    fun parseGeoResponse(inputStream: InputStream?): Pair<String, String>? {
        if (inputStream == null)
            return null

        val geoLocations: List<GeoLocation>

        try {
            geoLocations = gson.fromJson(InputStreamReader(inputStream), ArrayList<GeoLocation>().javaClass)
        } catch (e: JsonParseException) {
            return null
        }

        if (geoLocations.isEmpty())
            return null

        geoLocations[0].let {
            return Pair(it.latitude.toString(), it.longitude.toString())
        }
    }

    fun parseForecastResponse(inputStream: InputStream?): ForecastResponse? {
        if (inputStream != null) {
            try {
                return gson.fromJson(InputStreamReader(inputStream), ForecastResponse::class.java)
            } catch (e: JsonParseException) {}
        }

        return null
    }

}