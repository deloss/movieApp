package com.example.movieapp.ui.login

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


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