package com.example.soptseminar3.webtoon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soptseminar3.R

class WebtViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val img=itemView.findViewById<ImageView>(R.id.Webtitem_image)
    val tv_title=itemView.findViewById<TextView>(R.id.title)
    val tv_author=itemView.findViewById<TextView>(R.id.author)

    fun bind(data: WebtData){
        tv_title.text=data.title
        tv_author.text=data.author
        Glide.with(itemView).load(data.img_url).into(img)
    }

}