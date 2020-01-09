package com.example.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.vo.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE username=:username")
    fun findByUsername(username : String): Single<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : User) : Completable

}