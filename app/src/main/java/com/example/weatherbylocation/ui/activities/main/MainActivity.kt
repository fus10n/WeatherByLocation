package com.example.weatherbylocation.ui.activities.main

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity(), MainActivityView {

    private val presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}