package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myapplication.databinding.CustomDialogBinding


//다이얼로그 클래스
class CustomDialog(context : Context) : AppCompatActivity(){
    private val dialog = Dialog(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding : CustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
//        val rootview = binding.root
//        setContentView(rootview)
        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,"database"
        ).allowMainThreadQueries().build()
        //db객체 생성
    }

    fun show(){
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val ok = dialog.findViewById<TextView>(R.id.ok3)
        ok.setOnClickListener {
            dialog.dismiss()
        }
    }

}