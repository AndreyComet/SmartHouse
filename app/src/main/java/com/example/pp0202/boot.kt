package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.window.SplashScreen

class boot : AppCompatActivity() {
    private val splashScreenDuration: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot)
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, splashScreenDuration)
    }
}