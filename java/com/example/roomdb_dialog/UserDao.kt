package com.example.roomdb_dialog

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user : User)

    @Delete
    fun delete(user : User)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Query("SELECT * FROM user")
    fun getAll() : List<User>

    @Query("SELECT name FROM user")
    fun getName() : String

    @Query("SELECT pw FROM user")
    fun getPw() : String

}