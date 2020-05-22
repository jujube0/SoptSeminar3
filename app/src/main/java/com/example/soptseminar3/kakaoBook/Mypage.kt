package com.example.soptseminar3.kakaoBook


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soptseminar3.R
import com.example.soptseminar3.customEnqueue
import com.example.soptseminar3.network.RequestToServer
import com.example.soptseminar3.showToast
import kotlinx.android.synthetic.main.fragment_mypage.*

/**
 * A simple [Fragment] subclass.
 */
class Mypage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data= mutableListOf<BookData>()

        val requestToServer=RequestToServer
        btn_search.setOnClickListener {
            val quest:String=edt_search.text.toString()
            requestToServer.k_service
                .requestSearchBook(
                    bookTitle = quest
                )
                .customEnqueue(
                    onSuccess = {
                        Log.d("book",it.documents?.title)
                        if(it.documents!=null) {
                            data.add(
                                BookData(
                                    imageurl = it.documents!!.image,
                                    title=it.documents!!.title,
                                    content=it.documents!!.contents,
                                    author = it.documents!!.authors[0]
                                )
                            )

                        }


                    }
                    ,onError = {
                        Log.d("book", "error occurred")
                    }
                )


        }

        val adapter:BookAdapter= BookAdapter(context!!)
        adapter.datas=data
        book_recyclerView.adapter=adapter
    }

}
