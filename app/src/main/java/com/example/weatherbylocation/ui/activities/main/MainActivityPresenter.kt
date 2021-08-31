package com.example.weatherbylocation.ui.activities.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.weatherbylocation.BuildConfig
import com.example.weatherbylocation.api.RequestExecutor
import com.example.weatherbylocation.domains.ForecastResponse

class MainActivityPresenter(private val activity: MainActivityView): RequestExecutor.Callback {

    private val handler = Handler(Looper.getMainLooper())

    private var requestRunnable: Runnable? = null

    fun onSearchTextChanged(text: String) {
        requestRunnable?.let { handler.removeCallbacks(it) }

        Runnable {
            requestRunnable = null
            activity.showLoading()
            RequestExecutor.executeWeatherRequest(text, this@MainActivityPresenter)
        }.let {
            requestRunnable = it
            handler.postDelayed(it, BuildConfig.SEARCH_DELAY_MILLIS)
        }
    }

    override fun onRequestResult(response: ForecastResponse) {
        activity.loadForecast()

        Log.d(this::class.java.simpleName, response.toString())
    }

    override fun onRequestError(message: String?) {
        activity.showError(message)

        Log.d(this::class.java.simpleName, message?.ifEmpty { null } ?: "null")
    }

}