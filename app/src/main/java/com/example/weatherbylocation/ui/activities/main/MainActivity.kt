package com.example.weatherbylocation.ui.activities.main

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.weatherbylocation.databinding.ActivityMainBinding

class MainActivity : Activity(), MainActivityView {

    private val presenter = MainActivityPresenter(this)

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                presenter.onSearchTextChanged(s.toString())
            }
        })
    }

    override fun showLoading() {
        viewBinding.spinner.visibility = View.VISIBLE
    }

    override fun loadForecast() {
        viewBinding.txtError.text = ""
        viewBinding.txtError.visibility = View.GONE

        // TODO: Load weather details into ViewPager Fragments

        viewBinding.spinner.visibility = View.GONE
    }

    override fun showError(message: String?) {
        viewBinding.txtError.text = message
        viewBinding.txtError.visibility = View.VISIBLE
        viewBinding.spinner.visibility = View.GONE
    }

}