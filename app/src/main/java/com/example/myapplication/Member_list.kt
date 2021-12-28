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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : MemberlistBinding = MemberlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"database"
        ).allowMainThreadQueries().build()//같은 db

        binding.memberListId.setText(db.userDao().getID())

        binding.info.setOnClickListener {
            val customdialog = CustomDialog(this)
//            dialog.findViewById<TextView>(R.id.sign_up_id3).setText("dd")
//            dialog.findViewById<TextView>(R.id.sign_up_pw3).setText(db.userDao().getPW())
//            dialog.findViewById<TextView>(R.id.sign_up_pwcheck3).setText(db.userDao().getPWCHECK())
//            dialog.findViewById<TextView>(R.id.sign_up_name3).setText(db.userDao().getNAME())
//            dialog.findViewById<TextView>(R.id.sign_up_gender3).setText(db.userDao().getGENDER())

            customdialog.show()
        }

    }
}