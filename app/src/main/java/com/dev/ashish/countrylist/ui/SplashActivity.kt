package com.dev.ashish.countrylist.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.dev.ashish.countrylist.R
import java.util.*

//
// Created by Ashish on 01/05/21.
//

class SplashActivity : BaseActivity() {

    private val SPLASH_TIME_OUT: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        setupSplashTimer()
    }

    /**
     * This method will set timer and will launch next screen
     */
   private fun setupSplashTimer() {
        var timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                navigateToNextScreen()
            }
        }, SPLASH_TIME_OUT)
    }

    /**
     * This method will navigate to Home Screen
     */
    private fun navigateToNextScreen() {
        if(!isFinishing) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}