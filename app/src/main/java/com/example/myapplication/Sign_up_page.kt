package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.databinding.SignUpPageBinding

class Sign_up_page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding : SignUpPageBinding = SignUpPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Room.databaseBuilder(
            applicationContext, UserDatabase::class.java,"UserSign"
        ).allowMainThreadQueries().build()

        val id : String = binding.signUpId.text.toString()
        val pw : String = binding.signUpPw.text.toString()
        val pwcheck : String = binding.signUpPwcheck.text.toString()
        val name : String = binding.signUpName.text.toString()
        val gender : String = binding.signUpGender.text.toString()


        val newUser = User(id, pw, pwcheck, name, gender)

        binding.button.setOnClickListener {
            db.userDao().insertUser(newUser)
        }

        binding.signUp2.setOnClickListener {
            //db.userDao().deleteAll()

            binding.check.setText(db.userDao().getAll().joinToString(",", "{", "}"))
        }


    }
}