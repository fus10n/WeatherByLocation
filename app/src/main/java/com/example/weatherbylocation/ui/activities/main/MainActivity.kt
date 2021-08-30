package com.example.weatherbylocation.ui.activities.main

import android.app.Activity
import android.os.Bundle
import com.example.weatherbylocation.databinding.ActivityMainBinding

class MainActivity : Activity(), MainActivityView {

    private val presenter = MainActivityPresenter(this)

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

}