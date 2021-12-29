package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserSign")
data class User(
    var email : String = "",
    var pw : String = "",
    var pwcheck : String = "",
    var name : String = "",
    var gender : String = ""
    ){
    @PrimaryKey(autoGenerate = true)
    var ID : Int = 0
}