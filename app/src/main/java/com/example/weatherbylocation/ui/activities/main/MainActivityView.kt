package com.example.weatherbylocation.ui.activities.main

interface MainActivityView {

    fun showLoading()

    fun loadForecast()

    fun showError(message: String?)

}