package com.example.rick_and_morty

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    private var keep = true
    private val delay = 2000L
    private val handler = Handler(Looper.getMainLooper())

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        Log.d("MyLog", "$splashScreen")
        splashScreen.setKeepOnScreenCondition { keep }
        handler.postDelayed({
            keep = false
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.place_holder, MainFragment())
                .commit()
        }, delay)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLog", "Main")




    }
}