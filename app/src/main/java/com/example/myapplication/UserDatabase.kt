package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
//    companion object {
//        private var instance: UserDatabase? = null

//        @Synchronized
//        fun getInstance(context: Context): UserDatabase? {
//            if (instance == null) {
//                instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    "user-database"
//                ).build()
//            }
//            return instance
//        }
//    }
    //db생성 위치 UserDatabase클래스 or Sign_up_page클래스 ??

}