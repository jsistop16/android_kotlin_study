package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {


    lateinit var gender : String
    var signUpPageDb = LoginActivity.db
    var genderCheck = false //성별 체크여부
    lateinit var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.delete.setOnClickListener {
            deleteUser()
        }

        binding.signUp2.setOnClickListener {
            //회원가입 란 모두 입력시 회원가입
            var id = binding.signUpId.text.toString()
            var pw = binding.signUpPw.text.toString()
            var pwCheck = binding.signUpPwCheck.text.toString()
            var name = binding.signUpName.text.toString()
            if(binding.radioButtonMale.isChecked || binding.radioButtonFemale.isChecked){
                genderCheck = true
            }
            if(id.isEmpty() || pw.isEmpty() || pwCheck.isEmpty() || name.isEmpty() || !genderCheck){
                Toast.makeText(this, "모든 정보를 입력해 주세요", Toast.LENGTH_LONG).show()
            }else{
                //비밀번호 형식에 맞게 입력했는지
                if(!isPasswordFormat(binding.signUpPw.text.toString())){
                    //비밀번호 형식에 맞지 않는 경우
                    Toast.makeText(this,"올바르지 않은 형식의 비밀번호 입니다", Toast.LENGTH_LONG).show()
                }
                else{ //비밀번호 일치 여부 확인
                    if(pw != pwCheck){
                        Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show()
                    }else{
                        addUser()
                        lookUp()//db에 추가되는지 확인용도
                        Toast.makeText(this,"회원가입 완료", Toast.LENGTH_LONG).show()
                    }

                }
            }


        }
    }

    //비밀번호 조건
    fun isPasswordFormat(pw : String) : Boolean{
        var pwFormat = "^(?=.*[a-zA-Z!@#$%^&*0-9])(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*])(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{10,}\$"
        return pw.matches(pwFormat.toRegex())
    }

    //db에 user추가
    private fun addUser(){
        var email = binding.signUpId.text.toString()
        var pw = binding.signUpPw.text.toString()
        var pwCheck = binding.signUpPwCheck.text.toString()
        var name = binding.signUpName.text.toString()
        var gender = ""
        if(binding.radioButtonMale.isChecked){
            gender = "male"
        }else{
            gender = "female"
        }

        signUpPageDb.userDao().insertUser(User(email, pw, pwCheck, name, gender))
    }


    //db 데이터 확인용 메소드
    private fun lookUp(){
        var userInfo = signUpPageDb.userDao().getAll()
        binding.check.setText(userInfo.joinToString(",","{","}"))
    }

    //db 데이터 삭제(확인용)
    private fun deleteUser(){
        signUpPageDb.userDao().deleteAll()
    }
}