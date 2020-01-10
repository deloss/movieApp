package com.example.movieapp.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.movieapp.data.database.UserDB
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(context : Context) : ViewModel() {

    private val dbHandler = UserDB.getInstance(context)

    private val compositeDisposable = CompositeDisposable()

    val loginState = MutableLiveData<LoginState>()

    fun onClickLogin(username : String, password : String) {
        compositeDisposable.add(dbHandler.userDao().findByUsername(username).subscribeOn(
            Schedulers.io()).subscribe(
            {
                if(it.username == username && it.password == password)
                    loginState.postValue(LoginState.SUCCESS)
                else
                    loginState.postValue(LoginState.LOGIN_FAILED)
            },
            {
                Log.e("LoginViewModel", it.message)
                loginState.postValue(LoginState.ERROR)
            }
        ))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}