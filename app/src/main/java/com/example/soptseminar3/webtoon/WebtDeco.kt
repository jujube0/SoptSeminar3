package com.example.soptseminar3.webtoon

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class WebtDeco : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 항목의 index값 획득
        val index = parent.getChildAdapterPosition(view)+1
        if(index%3==0){
            //여백
            outRect.set(20,20,20,60)
        }else outRect.set(20,20,20,20)
        //itemview의 배경색을 바꾼다.
        view.setBackgroundColor(Color.parseColor("#ffebee"))
        //떠 있는 효과
        ViewCompat.setElevation(view,20.0f)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val width = parent.width
        val height = parent.height

        val paint = Paint()
        paint.setColor(Color.parseColor("#ffcdd2"))
        var rec = Rect(0,0,width/3,height)
        c.drawRect(rec,paint)

        paint.setColor(Color.parseColor("#e1bee7"))
        rec = Rect(width/3,0,width/3*2,height)
        c.drawRect(rec, paint)

        paint.setColor(Color.parseColor("#c5cae9"))
        rec = Rect(width/3*2,0,width,height)
        c.drawRect(rec,paint)

    }

}