package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.MemberlistBinding

class Member_list : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : MemberlistBinding = MemberlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.info.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.show()
        }

    }
}