package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//recyclerView Adapter
class ProfileAdapter(val profileList : ArrayList<User>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>() {

    var recyclerDb = LoginActivity.db
    var context: Context? = null//

    //뷰홀더가 생성 되었을때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)//매개변수 root값 false가 의미하는것?
        return CustomViewHolder(view)
    }

    //뷰의 재사용 & 데이터 넣어주는 부분
    //뷰와 뷰홀더가 묶였을때
    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.memberId_id.text = profileList[position].email
        holder.memberInfo_pw.text = "회원정보\n확인"

        val item = profileList[position]
        holder.apply {
            bind(item)
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //아이템들의 id값 참조
        val memberId_id = itemView.findViewById<TextView>(R.id.memberId)
        val memberInfo_pw = itemView.findViewById<TextView>(R.id.memberInfo)

        var view : View = itemView
        fun bind(item : User){
            view.findViewById<TextView>(R.id.memberInfo).setOnClickListener {
                //클릭시 다이얼로그창 띄우기
                var builder = AlertDialog.Builder(context)
                builder.setTitle("회원정보")
                builder.setMessage("아이디: " + item.email + "\n비밀번호: " + item.pw + "\n이름: " + item.name + "\n성별: " + item.gender)
                builder.show()
            }
        }
    }
}