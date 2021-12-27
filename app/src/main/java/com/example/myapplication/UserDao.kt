package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM UserSign")
    fun getAll() : Array<User>

    @Delete
    fun delete(userInfo: User)

    @Query("DELETE FROM UserSign")
    fun deleteAll()

    @Query("SELECT * FROM UserSign WHERE name ='race' ")
    fun getSpecific() : Array<User>

    //memberList에서 특정 회원정보 조회
    //@Query("select * from UserSign where " ")
    //fun getSpecific() : List<User>
}