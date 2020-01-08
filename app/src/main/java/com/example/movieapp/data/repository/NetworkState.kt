package com.example.movieapp.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
}

class NetworkState(val status : Status, val msg : String) {

    companion object {
        val LOADED = NetworkState(Status.SUCCESS, "Success")
        val LOADING = NetworkState(Status.RUNNING, "Running")
        val ERROR = NetworkState(Status.FAILED, "Error")
        val ENDOFLIST = NetworkState(Status.FAILED, "You have reached the end")
    }

}