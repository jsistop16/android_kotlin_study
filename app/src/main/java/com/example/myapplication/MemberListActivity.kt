package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.MemberlistBinding

class MemberListActivity : AppCompatActivity() {

    var memberListDb = MainActivity.db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : MemberlistBinding = MemberlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.memberListId.setText(memberListDb.userDao().getEmail())
        //다이얼로그 수정
        val customDialog : CustomDialogActivity = CustomDialogActivity(this)
        binding.info.setOnClickListener {
            customDialog.show()
        }

    }
}