package com.example.rick_and_morty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLog", "Main")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, MainFragment())
            .commit()
    }
}