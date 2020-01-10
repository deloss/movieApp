package com.example.movieapp.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.database.UserDB
import com.example.movieapp.data.vo.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignUpViewModel(context : Context) : ViewModel() {

    private val dbHandler = UserDB.getInstance(context)

    private val compositeDisposable = CompositeDisposable()

    val signUpState = MutableLiveData<LoginState>()

    fun onClickSignUp(username : String, password : String) {
        compositeDisposable.add(dbHandler.userDao().insertUser(User(username, password)).subscribeOn(
            Schedulers.io()).subscribe({
                signUpState.postValue(LoginState.SUCCESS)
        },
            {
                signUpState.postValue(LoginState.SIGN_UP_FAILED)
                Log.e("SignUpViewModel", it.message)
            }))
    }

}