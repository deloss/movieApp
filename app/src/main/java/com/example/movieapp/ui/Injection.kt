package com.example.movieapp.ui

import android.content.Context
import com.example.movieapp.ui.login.LoginViewModelFactory
import com.example.movieapp.ui.login.SignUpViewModelFactory

object Injection {

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        return LoginViewModelFactory(context)
    }

    fun provideSignUpViewModelFactory(context: Context): SignUpViewModelFactory {
        return SignUpViewModelFactory(context)
    }
}