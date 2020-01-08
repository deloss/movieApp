package com.example.movieapp.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val username: String?,
    @ColumnInfo val password: String?
)