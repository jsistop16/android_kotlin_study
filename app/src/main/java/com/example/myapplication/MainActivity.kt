package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.TextView
import com.example.myapplication.databinding.SignInBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : SignInBinding = SignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


//        val db = UserDatabase.getInstance(applicationContext)


        binding.signup.setOnClickListener{
            val intent = Intent(this, Sign_up_page::class.java)
            startActivity(intent)
        }
        binding.memberlist.setOnClickListener{
            intent = Intent(this, Member_list::class.java)
            startActivity(intent)
        }

    }

}