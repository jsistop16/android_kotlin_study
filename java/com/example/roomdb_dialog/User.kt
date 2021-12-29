package com.example.roomdb_dialog

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    var name: String = "",
    var pw: String = ""
    ){
    @PrimaryKey(autoGenerate = true)
    var ID : Int = 0
}
