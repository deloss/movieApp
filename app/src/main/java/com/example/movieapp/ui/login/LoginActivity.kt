package com.example.movieapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.ui.Injection
import com.example.movieapp.ui.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModelFactory : LoginViewModelFactory
    //val viewModel = LoginViewModel(this)

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModelFactory = Injection.provideViewModelFactory(this)
        login_button.setOnClickListener { viewModel.onClickLogin(username_field.text.toString(), password_field.text.toString()) }
        viewModel.loginState.observe(this, Observer {
            login_info.text = if(it == LoginState.SUCCESS) "Login exitoso!"
            else if (it == LoginState.ERROR) "El usuario no existe"
            else if (it == LoginState.LOGIN_FAILED) "El usuario o contraseña son incorrectos"
            else ""
        })
    }


}
