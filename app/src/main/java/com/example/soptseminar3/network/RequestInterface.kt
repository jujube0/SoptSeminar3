package com.example.soptseminar3.network

import com.example.soptseminar3.R
import com.example.soptseminar3.data.RequestLogin
import com.example.soptseminar3.data.RequestSignUp
import com.example.soptseminar3.data.ResponseLogin
import com.example.soptseminar3.data.ResponseSignUp
import com.example.soptseminar3.kakaoBook.BookData
import com.example.soptseminar3.kakaoBook.ResponseBookData
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {
    @POST("/user/signin")
    fun requestLogin(@Body body: RequestLogin) :Call<ResponseLogin>

    @POST("/user/signup")
    fun requestSignup(@Body body: RequestSignUp) : Call<ResponseSignUp>

    @Headers("Authorization: KakaoAK "+ R.string.app_key)
    @GET("/v3/search/book")
    fun requestSearchBook(
        @Query("query")bookTitle:String
    ):Call<ResponseBookData>
}
