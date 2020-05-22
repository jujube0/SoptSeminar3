package com.example.soptseminar3.data

import android.app.Application
import com.example.soptseminar3.R
import com.kakao.sdk.common.KakaoSdk

class App : Application(){
    companion object{
        lateinit var prefs: LoginPreference
    }
    override fun onCreate() {
        prefs = LoginPreference(applicationContext)
        super.onCreate()
        KakaoSdk.init(this, R.string.app_key.toString())

    }

}