package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.MemberlistBinding
import kotlinx.android.synthetic.main.item_view.view.*

class MemberListActivity : AppCompatActivity() {

    var memberListDb = LoginActivity.db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : MemberlistBinding = MemberlistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val memberList = memberListDb.userDao().getAll() as ArrayList//형변환
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)//어댑터의 방향 결정
        binding.recyclerView.setHasFixedSize(true)//어뎁터의 성능 고려
        binding.recyclerView.adapter = ProfileAdapter(memberList)//어뎁터에 리스트 넣기

//        binding.recyclerView.memberId.setOnClickListener {
//            binding.recyclerView.memberId.setText("!!")
//        }
    }
}