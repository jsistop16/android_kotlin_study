package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.myapplication.databinding.SignInBinding

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var db : UserDatabase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : SignInBinding = SignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"database"
        ).allowMainThreadQueries().build()


        binding.signup.setOnClickListener{
            val intent = Intent(this, Sign_up_page::class.java)
            startActivity(intent)
        }
        binding.memberlist.setOnClickListener{
            val intent = Intent(this, Member_list::class.java)
            startActivity(intent)
        }


        binding.signIn.setOnClickListener {
            var putEmail = binding.id.text.toString()
            var putPw = binding.pw.text.toString()
            if(putEmail == db.userDao().getEmail() && putPw == db.userDao().getPW()){
                Toast.makeText(this,"로그인 성공", Toast.LENGTH_LONG).show()
                val intent = Intent(this, AfterLogin::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"로그인 실패", Toast.LENGTH_LONG).show()
            }
        }

    }

}