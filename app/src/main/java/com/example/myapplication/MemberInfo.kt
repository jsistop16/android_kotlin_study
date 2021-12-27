package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.MemberInfoBinding

class MemberInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        var binding : MemberInfoBinding = MemberInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }



}