package com.example.roomdb_dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialog(context : Context) : AppCompatActivity() {
    private val dialog = Dialog(context)
    //dialog창에서 뷰바인딩 사용??
//    private lateinit var db : UserDatabase
    val this_db = MainActivity.db


    fun show(){
        dialog.setContentView(R.layout.custom_dialog)
        //dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setTitle("회원정보")
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.id.setText(this_db.userDao().getName())
        dialog.pw.setText(this_db.userDao().getPw())
        dialog.show()
        dialog.ok.setOnClickListener {
            dialog.dismiss()
        }
    }




}