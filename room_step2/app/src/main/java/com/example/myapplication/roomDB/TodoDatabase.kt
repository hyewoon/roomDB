package com.example.myapplication.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        /* @Volatile = 접근가능한 변수의 값을 cache를 통해 사용하지 않고
       thread가 직접 main memory에 접근 하게하여 동기화. */

        private var instance: TodoDatabase? = null



        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        @Synchronized
        fun getInstance(context: Context): TodoDatabase? {
            if (instance == null) {
                synchronized(TodoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "user-database"
                    )
                        .build()
                }
            }
            return instance
        }
    }

}