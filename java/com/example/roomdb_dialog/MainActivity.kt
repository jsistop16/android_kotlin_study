package com.example.roomdb_dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var db : UserDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
//        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        val rootview = binding.root

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"user-database"
        ).allowMainThreadQueries().build()

        setContentView(R.layout.activity_main)

        val cdialog : CustomDialog = CustomDialog(this)
        dialog.setOnClickListener {
            cdialog.show()
        }

        btn.setOnClickListener {
            addUser()
            refreshUserList()
        }

        delete.setOnClickListener {
            db.userDao().deleteAll()
        }

    }

    private fun refreshUserList(){
        var users = db.userDao().getAll()
        check.setText(users.joinToString(",","{","}"))
    }

    private fun addUser(){
        var id = user_id.text.toString()
        var pw = user_pw.text.toString()
        db.userDao().insert(User(id, pw))
    }
}