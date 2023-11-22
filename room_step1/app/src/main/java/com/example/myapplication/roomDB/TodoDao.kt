package com.example.myapplication.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*
 data access object
* todo 테이블의 데이터와 상호 작용
* */
@Dao
interface TodoDao {

    @Insert
    fun insert( todo : Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun getAll() : List<Todo>
}