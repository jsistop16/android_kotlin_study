package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.CustomDialogBinding

//다이얼로그 창 클래스
class CustomDialog(context : Context) : AppCompatActivity(){
    private val dialog = Dialog(context)
//    val db = UserDatabase.getInstance(applicationContext)
    fun show(){
        dialog.setContentView(R.layout.custom_dialog)

        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()
//        val roomcheck = findViewById<TextView>(R.id.roomcheck)
//        roomcheck.setText(db!!.userDao().getSpecific().toString())
        val ok = dialog.findViewById<TextView>(R.id.ok3)
        ok.setOnClickListener {
            dialog.dismiss()
        }



    }

}