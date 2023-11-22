package com.example.myapplication.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey val id: Int,
    var content : String
)
