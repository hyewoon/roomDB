package com.example.myapplication.roomDB

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

/*
* 데이터베이스를 보유할 AppDatabase 클래스를 정의합니다.
*  AppDatabase는 데이터베이스 구성을 정의하고 영구 데이터에 대한 앱의 기본 액세스 포인트 역할을 합니다.
* 데이터베이스 클래스는 다음 조건을 충족해야 합니다.
*
* 데이터베이스와 연결된
* 각 DAO 클래스에서 데이터베이스 클래스는 인수가 0개이고 DAO 클래스의 인스턴스를 반환하는 추상 메서드를 정의해야 합니다.
* */

@Database(  version = 2,
    entities = [Todo::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2,
            spec = TodoDatabase.MyAutoMigration::class
        )
    ])
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    @RenameTable(fromTableName = "Todo", toTableName = "Todo")
    class MyAutoMigration : AutoMigrationSpec

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }

}





