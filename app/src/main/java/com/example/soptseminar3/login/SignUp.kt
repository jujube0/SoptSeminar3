package com.example.soptseminar3.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.soptseminar3.R
import com.example.soptseminar3.customEnqueue
import com.example.soptseminar3.data.App
import com.example.soptseminar3.data.RequestSignUp
import com.example.soptseminar3.data.ResponseSignUp
import com.example.soptseminar3.network.RequestToServer
import com.example.soptseminar3.showToast
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val requestToServer=RequestToServer
//        var id:String
//        var pw:String

        //회원가입 버튼 눌렸을 경우
        btn_signup.setOnClickListener {

            if(!et_id.text.isNullOrEmpty()&&!et_pw.text.isNullOrEmpty()&&!et_email.text.isNullOrEmpty()
                    && !et_name.text.isNullOrEmpty() && !et_phoneNum.text.isNullOrEmpty()) {
                requestToServer.service.requestSignup(
                    RequestSignUp(
                        id = et_id.text.toString(),
                        password = et_pw.text.toString(),
                        email = et_email.text.toString(),
                        name = et_name.text.toString(),
                        phone = et_phoneNum.text.toString()
                    )
                ).customEnqueue(
                    onError = { showToast("error") },
                    onSuccess = {
                        if (it.success) {
                        Log.d("signup",it.success.toString())
                            showToast("회원가입되었습니다")
                            App.prefs.id=et_id.text.toString()
                            App.prefs.pw=et_pw.text.toString()
                            finish()
                        }else{
                            showToast(it.message)
                        }
                    }
                )
            }
        }

    }

}
