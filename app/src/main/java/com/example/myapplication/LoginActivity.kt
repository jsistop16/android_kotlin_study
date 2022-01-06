package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
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
            try{
                val intent = Intent(this, MemberListActivity::class.java)
                startActivity(intent)
            }catch (e : Exception){
                ActivityCompat.finishAffinity(this)
                System.exit(0)
            }
        }


        binding.signIn.setOnClickListener {
            var putEmail = binding.id.text.toString()
            var putPw = binding.pw.text.toString()
            if(putEmail.isNotEmpty() && putPw.isNotEmpty()){//ID, PW 모두 입력했을때
                if(putPw == db.userDao().getPwByEmail(putEmail)){//입력한 ID값의 PW와 입력한 PW가 일치할때
                    Toast.makeText(this,"로그인 성공", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, AfterLoginActivity::class.java)
                    startActivity(intent)
                }
                else{//ID와 PW가 맞지 않을 때
                    Toast.makeText(this,"가입된 계정이 없습니다", Toast.LENGTH_LONG).show()
                }
            }else{//ID or PW 입력하지 않았을 때
                Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }
}