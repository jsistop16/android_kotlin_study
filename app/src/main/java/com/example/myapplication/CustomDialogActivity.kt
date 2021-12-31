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
class CustomDialogActivity(context : Context) : AppCompatActivity(){
    private val dialog = Dialog(context)

    var customDialogDb = MainActivity.db

    fun show(){
        dialog.setContentView(R.layout.custom_dialog)
        //dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.signUpIdDialog.setText("email: " + customDialogDb.userDao().getEmail())
        dialog.signUpPwDialog.setText("pw: " + customDialogDb.userDao().getPw())
        dialog.signUpPwCheckDialog.setText("pw check: " + customDialogDb.userDao().getPwCheck())
        dialog.signUpNameDialog.setText("name: " + customDialogDb.userDao().getName())
        dialog.signUpGenderDialog.setText("gender: " + customDialogDb.userDao().getGender())
        dialog.show()

        dialog.okDialog.setOnClickListener {
            dialog.dismiss()
        }
    }

}