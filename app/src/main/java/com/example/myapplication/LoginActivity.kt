package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    companion object{
        lateinit var db : UserDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding : ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"database"
        ).allowMainThreadQueries().build()
        //allowMainThreadQueries() <- 개발시에만 적용


        binding.signUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.memberList.setOnClickListener {
            //memberList에 데이터 없을경우 앱 종료(예외처리)
            val intent = Intent(this, MemberListActivity::class.java)
            startActivity(intent)
        }


        binding.signIn.setOnClickListener {
            var putEmail = binding.id.text.toString()
            var putPw = binding.pw.text.toString()
            if(putEmail == db.userDao().getEmail() && putPw == db.userDao().getPw()){
                Toast.makeText(this,"로그인 성공", Toast.LENGTH_LONG).show()
                val intent = Intent(this, AfterLoginActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"로그인 실패", Toast.LENGTH_LONG).show()
            }
        }


    }

}