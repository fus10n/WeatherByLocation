package com.example.weatherbylocation.api

import android.os.Handler
import android.os.Looper
import com.example.weatherbylocation.BuildConfig
import com.example.weatherbylocation.domains.ForecastResponse
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object RequestExecutor {

    interface Callback {
        fun onRequestResult(response: ForecastResponse)
        fun onRequestError(message: String?)
    }

    private val mainHandler = Handler(Looper.getMainLooper())

    private val apiKeyPair = Pair("appid", BuildConfig.API_KEY)
    private val weatherExcludePair = Pair("exclude", "alerts")

    private fun initGeocodingConnection(address: String): HttpURLConnection? =
        try {
            (URL(BuildConfig.GEOCODING_API_URL).openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = BuildConfig.NET_REQUEST_TIMEOUT_MILLIS
                doInput = true
                setRequestProperty("Content-Type", "application/json")
                RequestUtils.writeRequestParams(
                    this,
                    Pair("q", address),
                    apiKeyPair
                )
                connect()
            }
        } catch (e: IOException) {
            null
        }

    private fun initWeatherConnection(lat: String, lon: String): HttpsURLConnection? =
        try {
            (URL(BuildConfig.WEATHER_API_URL).openConnection() as HttpsURLConnection).apply {
                requestMethod = "GET"
                connectTimeout = BuildConfig.NET_REQUEST_TIMEOUT_MILLIS
                doInput = true
                setRequestProperty("Content-Type", "application/json")
                RequestUtils.writeRequestParams(
                    this,
                    Pair("lat", lat),
                    Pair("lon", lon),
                    weatherExcludePair,
                    apiKeyPair
                )
                connect()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    fun executeWeatherRequest(address: String, callback: Callback) {
        Thread(Runnable {
            val geocodingConnection = initGeocodingConnection(address)
            if (geocodingConnection == null) {
                mainHandler.post { callback.onRequestError("weather API connection error") }
                return@Runnable
            }

            if (geocodingConnection.responseCode !in 200..399) {
                mainHandler.post { callback.onRequestError(geocodingConnection.responseMessage) }
                return@Runnable
            }

            val geoPair = ContentParser.parseGeoResponse(geocodingConnection.inputStream)
            if (geoPair == null) {
                mainHandler.post { callback.onRequestError("null geo location parse result") }
                return@Runnable
            }

            val weatherConnection = initWeatherConnection(geoPair.first, geoPair.second)
            if (weatherConnection == null) {
                mainHandler.post { callback.onRequestError("weather API connection error") }
                return@Runnable
            }

            if (weatherConnection.responseCode !in 200..399) {
                mainHandler.post { callback.onRequestError(weatherConnection.responseMessage) }
                return@Runnable
            }

            val forecast = ContentParser.parseForecastResponse(weatherConnection.inputStream)
            if (forecast == null) {
                mainHandler.post { callback.onRequestError("null forecast parse result") }
                return@Runnable
            }

            mainHandler.post { callback.onRequestResult(forecast) }
        }).start()
    }

}