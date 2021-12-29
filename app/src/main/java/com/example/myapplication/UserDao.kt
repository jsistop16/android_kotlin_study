package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM UserSign")
    fun getAll() : List<User>

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM UserSign")
    fun deleteAll()

    @Query("SELECT email FROM UserSign")
    fun getEmail() : String

    @Query("SELECT pw FROM UserSign")
    fun getPW() : String

    @Query("SELECT pwcheck FROM UserSign")
    fun getPWCHECK() : String

    @Query("SELECT name FROM UserSign")
    fun getNAME() : String

    @Query("SELECT gender FROM UserSign")
    fun getGENDER() : String


    //memberList에서 특정 회원정보 조회
    //@Query("select * from UserSign where " ")
    //fun getSpecific() : List<User>
}