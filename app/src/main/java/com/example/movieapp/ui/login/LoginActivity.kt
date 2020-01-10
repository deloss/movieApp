package com.example.movieapp.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.ui.Injection
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModelFactory : LoginViewModelFactory

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModelFactory = Injection.provideLoginViewModelFactory(this)
        login_button.setOnClickListener { viewModel.onClickLogin(username_field.text.toString(), password_field.text.toString()) }
        viewModel.loginState.observe(this, Observer {
            when (it) {
                LoginState.SUCCESS -> {
                    val prefs = this.getSharedPreferences(
                        "com.example.movieapp", Context.MODE_PRIVATE
                    )
                    prefs.edit().putBoolean("logged", true).commit()
                    finish()
                }
                LoginState.ERROR -> login_info.text = "El usuario no existe"
                LoginState.LOGIN_FAILED -> login_info.text = "El usuario o contraseña son incorrectos"
            }
        })
        sign_up_button.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
    }


}
