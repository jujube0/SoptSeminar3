package com.example.soptseminar3.kakaoBook

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soptseminar3.R

class BookAdapter(private val context: Context): RecyclerView.Adapter<BookViewHolder>() {

  	var datas: MutableList<BookData> = mutableListOf()

  	// xml file을 inflate한 후 viewHolder를 만든다.
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
  	    val view = LayoutInflater.from(context).inflate(R.layout.kakao_recycler_item, parent,false)
  		return BookViewHolder(view)
      }

      override fun getItemCount(): Int {
  		 return datas.size
      }
  	  // viewholder의 항목을 구성하기 위해 호출된다.
      override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
          holder.bind(datas[position])
      }
  }