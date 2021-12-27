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

        val newUser = User(binding.signUpId.text.toString(),
            binding.signUpPw.text.toString(),
            binding.signUpPwcheck.text.toString(),
            binding.signUpName.text.toString(),
            binding.signUpGender.text.toString())

        binding.button.setOnClickListener {
            db.userDao().insertUser(newUser)
        }

        binding.signUp2.setOnClickListener {
            //db.userDao().deleteAll()

            binding.check.setText(db.userDao().getSpecific().joinToString(",", "{", "}"))
        }


    }
}