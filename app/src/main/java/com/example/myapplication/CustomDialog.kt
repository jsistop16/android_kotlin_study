package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.databinding.CustomDialogBinding
import kotlinx.android.synthetic.main.custom_dialog.*



//다이얼로그 클래스
class CustomDialog(context : Context) : AppCompatActivity(){
    private val dialog = Dialog(context)

    var customdialog_db = MainActivity.db

    fun show(){
        dialog.setContentView(R.layout.custom_dialog)
        //dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.sign_up_id3.setText("email: " + customdialog_db.userDao().getEmail())
        dialog.sign_up_pw3.setText("pw: " + customdialog_db.userDao().getPW())
        dialog.sign_up_pwcheck3.setText("pw check: " + customdialog_db.userDao().getPWCHECK())
        dialog.sign_up_name3.setText("name: " + customdialog_db.userDao().getNAME())
        dialog.sign_up_gender3.setText("gender: " + customdialog_db.userDao().getGENDER())
        dialog.show()

        dialog.ok3.setOnClickListener {
            dialog.dismiss()
        }
    }

}