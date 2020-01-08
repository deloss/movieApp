package com.example.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.vo.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username=:username")
    fun findByUsername(username: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(username : String, password : String)

}