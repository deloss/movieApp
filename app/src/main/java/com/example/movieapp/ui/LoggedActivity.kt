package com.example.movieapp.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import com.example.movieapp.ui.popular_movies.LoginActivity


abstract class LoggedActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = this.getSharedPreferences(
            "com.example.app", Context.MODE_PRIVATE
        )
        if(!prefs.getBoolean("logged", false))
            startActivity(Intent(this, LoginActivity::class.java))
    }
}