package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.databinding.SignUpPageBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    var signUpPageDb = MainActivity.db
    lateinit var binding : SignUpPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.delete.setOnClickListener {
            deleteUser()
        }

        binding.signUp2.setOnClickListener {
            if(!isPasswordFormat(binding.signUpPw.text.toString())){
                //비밀번호 형식에 맞지 않는 경우
                Toast.makeText(this,"올바르지 않은 형식의 비밀번호 입니다", Toast.LENGTH_LONG).show()
            }
            else{
                addUser()
                lookUp()
                Toast.makeText(this,"회원가입 완료", Toast.LENGTH_LONG).show()
            }

        }


    }


    //비밀번호 조건
    fun isPasswordFormat(pw : String) : Boolean{
        var pwFormat = "^(?=.*[a-zA-Z!@#$%^&*0-9])(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*])(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{10,}\$"
        return pw.matches(pwFormat.toRegex())
    }

    //db에 user추가
    private fun addUser(){
        var email = binding.signUpId.text.toString()
        var pw = binding.signUpPw.text.toString()
        var pwCheck = binding.signUpPwCheck.text.toString()
        var name = binding.signUpName.text.toString()
        var gender = binding.signUpGender.text.toString()

        signUpPageDb.userDao().insertUser(User(email, pw, pwCheck, name, gender))
    }


    //db 데이터 확인용 메소드
    private fun lookUp(){
        var userInfo = signUpPageDb.userDao().getAll()
        binding.check.setText(userInfo.joinToString(",","{","}"))
    }

    //db 데이터 삭제(확인용)
    private fun deleteUser(){
        signUpPageDb.userDao().deleteAll()
    }
}