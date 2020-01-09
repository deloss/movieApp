package com.example.movieapp.ui

import android.content.Context

object Injection {

    fun provideViewModelFactory(context: Context): LoginViewModelFactory {
        return LoginViewModelFactory(context)
    }
}