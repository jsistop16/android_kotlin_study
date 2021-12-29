package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.AfterLoginBinding

class AfterLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: AfterLoginBinding = AfterLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }
}