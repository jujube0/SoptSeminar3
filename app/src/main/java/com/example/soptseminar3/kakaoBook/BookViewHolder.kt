package com.example.soptseminar3.kakaoBook

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soptseminar3.R

class BookViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    val title = itemView.findViewById<TextView>(R.id.book_title)
    val content = itemView.findViewById<TextView>(R.id.book_content)
    val imageview=itemView.findViewById<ImageView>(R.id.book_image)
    val author=itemView.findViewById<TextView>(R.id.book_author)

    fun bind(data : BookData){
        title.text=data.title
        content.text=data.content
        author.text=data.author
        Glide.with(itemView).load(data.imageurl).into(imageview)
    }
 }