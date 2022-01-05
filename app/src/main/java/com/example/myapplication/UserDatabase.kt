package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    //roomDB migration
    object MigrateDatabase{
        //버전별 변경내역 : 버전 1에서 2로 바뀔 때 적용되는 사항
        val MIGRATE_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                //변경 쿼리(ex. column추가)
                val alter = "ALTER table users add column age"
                //쿼리 적용
                database.execSQL(alter)
            }
        }
        //다음 버전 업데이트 적용되는 사항...

    }

}