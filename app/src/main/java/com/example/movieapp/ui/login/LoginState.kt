package com.example.movieapp.ui.login

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    LOGIN_FAILED,
    SIGN_UP_FAILED
}

class LoginState(val status : Status, val msg : String) {

    companion object {
        val SUCCESS = LoginState(Status.SUCCESS, "Success")
        val RUNNING = LoginState(Status.RUNNING, "Running")
        val ERROR = LoginState(Status.FAILED, "Error")
        val LOGIN_FAILED = LoginState(Status.LOGIN_FAILED, "Login failed")
        val SIGN_UP_FAILED = LoginState(Status.SIGN_UP_FAILED, "Sign up failed")
    }

}