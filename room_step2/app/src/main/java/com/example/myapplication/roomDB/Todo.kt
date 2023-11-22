package com.example.myapplication.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var list : String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
