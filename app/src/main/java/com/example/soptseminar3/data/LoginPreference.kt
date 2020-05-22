package com.example.soptseminar3.data

import android.content.Context
import android.content.SharedPreferences

class LoginPreference(context: Context){

    val PREF_FILENAME="prefs"
    val PREF_KEY_ID=""
    val PREF_KEY_PASSWORD=""

    val prefs:SharedPreferences=context.getSharedPreferences(PREF_FILENAME, 0)

    var id:String?
        get()=prefs.getString(PREF_KEY_ID,"")
        set(value)=prefs.edit().putString(PREF_KEY_ID,value).apply()

    var pw:String?
        get()=prefs.getString(PREF_KEY_PASSWORD,"")
        set(value)=prefs.edit().putString(PREF_KEY_PASSWORD,value).apply()

    fun clear(){
        id=""
        pw=""
    }

}