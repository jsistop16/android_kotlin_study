package com.example.myapplication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.builder
import com.example.myapplication.databinding.MemberlistBinding
import java.util.stream.DoubleStream.builder

class Member_list : AppCompatActivity() {

    var member_list_db = MainActivity.db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : MemberlistBinding = MemberlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.memberListId.setText(member_list_db.userDao().getEmail())
        val customdialog : CustomDialog = CustomDialog(this)
        binding.info.setOnClickListener {
            customdialog.show()
        }

    }
}