package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.databinding.SignUpPageBinding

class Sign_up_page : AppCompatActivity() {

    var Sign_up_page_db = MainActivity.db
    lateinit var binding : SignUpPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            deleteUser()
        }


        binding.signUp2.setOnClickListener {
            addUser()
            lookup()
            Toast.makeText(this,"회원가입 완료", Toast.LENGTH_LONG).show()
        }


    }
    private fun addUser(){
        var email = binding.signUpId.text.toString()
        var pw = binding.signUpPw.text.toString()
        var pwcheck = binding.signUpPwcheck.text.toString()
        var name = binding.signUpName.text.toString()
        var gender = binding.signUpGender.text.toString()

        Sign_up_page_db.userDao().insertUser(User(email, pw, pwcheck, name, gender))
    }

    private fun lookup(){
        var users = Sign_up_page_db.userDao().getAll()
        binding.check.setText(users.joinToString(",","{","}"))
    }

    private fun deleteUser(){
        Sign_up_page_db.userDao().deleteAll()
    }
}