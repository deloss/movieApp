package com.example.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.vo.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}