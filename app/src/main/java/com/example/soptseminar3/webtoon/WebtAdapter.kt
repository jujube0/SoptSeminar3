package com.example.soptseminar3.webtoon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar3.R

class WebtAdapter(private val context: Context):RecyclerView.Adapter<WebtViewHolder>() {

    var datas:MutableList<WebtData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebtViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_webt, parent, false)
        val width: Int = parent.getMeasuredWidth() / 3
        view.layoutParams.width = width
        view.requestLayout()
        return WebtViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: WebtViewHolder, pos: Int) {
        holder.bind(datas[pos])
    }

}