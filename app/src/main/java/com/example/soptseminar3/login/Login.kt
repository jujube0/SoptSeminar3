package com.example.soptseminar3.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.soptseminar3.R
import com.example.soptseminar3.customEnqueue
import com.example.soptseminar3.data.App
import com.example.soptseminar3.data.RequestLogin
import com.example.soptseminar3.data.ResponseLogin
import com.example.soptseminar3.network.RequestToServer
import com.example.soptseminar3.showToast
import com.example.soptseminar3.textChangedListener
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login.setOnClickListener {

            //id, pw edit text가 비어있지 않으면 서버로 data 전송
            if(!et_id.text.isNullOrEmpty()&&!et_pw.text.isNullOrEmpty()){

                login(et_id.text.toString(),et_pw.text.toString())

            }else{
                showToast("fill the all blanks")
            }
        }

        //id edittext 가 비어있으면 toast
        et_id.textChangedListener {
            if (it.isNullOrBlank()){
                this.showToast("비어있습니다")
            }
        }

        //회원가입하기 버튼이 눌렸을 경우 회원가입 페이지 열기
        btn_toSignup.setOnClickListener {
            val intent=Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    // id, pw가 저장되어있으면 바로 로그인
    override fun onResume() {
        super.onResume()
        if(!App.prefs.id.isNullOrBlank()&&!App.prefs.pw.isNullOrBlank()){
            login(App.prefs.id.toString(), App.prefs.pw.toString())
        }
    }
    //서버에 로그인 전송
    fun login(id:String, pw:String) {
        val requestToServer = RequestToServer

        requestToServer.service.requestLogin(
            RequestLogin(id, pw)
        ).customEnqueue(
            onError = { showToast("올바르지 않은 요청입니다") },
            onSuccess = {
                //if (it.success) {
                    Log.d("signon", it.success.toString())
                    Log.d("signon", it.message.toString())
                    Toast.makeText(this@Login, "로그인 성공", Toast.LENGTH_SHORT).show()
                    storeData(id, pw)
                    finish()
                //}
            }
        )
    }

    // app preference 에 id, pw 저장
    fun storeData(id:String,pw: String){
        App.prefs.id=id
        App.prefs.pw=pw
    }
    //pref 에 data저장

//
//        requestToServer.service.requestLogin(
//            RequestLogin(
//                id,pw
//            )
//        ).enqueue(object : Callback<ResponseLogin>{
//            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
//                Toast.makeText(this@Login, "아이디 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
//                if(response.isSuccessful) {//statusCode가 200~300사이일때 응답 body 이용 가능
//                    if(response.body()!!.success){ //response login의 success 가 true 인경우
//                        Toast.makeText(this@Login, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        finish()
//
//                    }else{
//                        Toast.makeText(this@Login, "아이디 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        })//Callback 등록, import retrofit call back

}
