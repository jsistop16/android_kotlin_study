package com.example.mycontextactivityexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontextactivityexample.databinding.ActivityMainBinding

class MainAdapter(var info : MemberInfo) :
    RecyclerView.Adapter<MainAdapter.MemberViewHolder>() {
    var memberList = arrayListOf(info)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.MemberViewHolder {

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MemberViewHolder, position: Int) {

        var item = memberList[position]
        holder.apply {
            bind(item)
        }
    }

    override fun getItemCount() = memberList.size

    inner class MemberViewHolder(private var viewBinding : ActivityMainBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(input : MemberInfo){
            MemberInfo(viewBinding.enrollIDEt.text, viewBinding.enrollPWEt.text)

        }

    }
}