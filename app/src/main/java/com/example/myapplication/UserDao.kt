package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getAll() : List<User>

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT email FROM users")
    fun getEmail() : String

    @Query("SELECT pw FROM users")
    fun getPw() : String

    @Query("SELECT pwCheck FROM users")
    fun getPwCheck() : String

    @Query("SELECT name FROM users")
    fun getName() : String

    @Query("SELECT gender FROM users")
    fun getGender() : String

    @Query("SELECT email FROM users WHERE ID= :i")
    fun getRequestEmail(i : Int) : String


    //memberList에서 특정 회원정보 조회
    //@Query("select * from UserSign where " ")
    //fun getSpecific() : List<User>
}