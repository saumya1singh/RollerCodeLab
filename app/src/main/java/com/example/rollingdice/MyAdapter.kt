package com.example.rollingdice

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.each_row.view.*

class MyAdapter(val context: Context, val list: ArrayList<MyModel>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(context).inflate( R.layout.each_row, p0,false ))

    }


    override fun getItemCount(): Int {

        return list.size

    }


    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        val current = list[p1]

        with(p0.itemView)
        {
            
            tv1.text = current.resulta.toString()

        }

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}