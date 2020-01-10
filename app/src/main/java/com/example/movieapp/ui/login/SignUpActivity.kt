package com.example.movieapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.ui.Injection
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModelFactory : SignUpViewModelFactory
    private val viewModel: SignUpViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        viewModelFactory = Injection.provideSignUpViewModelFactory(this)

        sign_up_button.setOnClickListener { viewModel.onClickSignUp(username_field.text.toString(), password_field.text.toString()) }
        viewModel.signUpState.observe(this, Observer {
            if(it == LoginState.SUCCESS)
                startActivity(Intent(this, LoginActivity::class.java))
            else
                login_info.text = "Error in sign up"
        })
    }
}
