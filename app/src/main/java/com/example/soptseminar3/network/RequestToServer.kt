package com.example.soptseminar3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.209.144.115:3002")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var kakao_retrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
    var k_service:RequestInterface = kakao_retrofit.create(RequestInterface::class.java)
}