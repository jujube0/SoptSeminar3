# SoptSeminar3
Retrofit2

서버와 HTTP 통신을 도와주는 라이브러리

​

서버 연동을 실행하는 객체는 Call 객체. 하지만 얘는 개발자가 만드는 게 아니다. 

개발자가 네트워킹 때 호출할 함수를 인터페이스에 등록하여 Retrofit에 전달하면

 -> Retrofit에서 인터페이스 함수를 구현해서  Service객체를 반환. Service 객체가 Call 객체 반환하게 하는 것.

Service 내 함수는 개발자가 등록한 인터페이스와 같은 이름으로 구현된다.

​

- service 객체 획득하여 네트워킹 필요한 순간 service의 함수를 호출하여 call객체 이용하면 되는 것.

​

서버 연동시 JSON, XML을 이용하게 되는데, 이를 자동으로 파싱하여 개발자가 만든 VO객체로 변환해주는 커버터가 필요하다. 

이는 레트로핏과 연동되는 외부 라이브러리를 이용

implementation 'com.google.code.gson:gson:2.8.6'
1. Model 정의

서버 연동을 위한 데이터 추상화 클래스. 컨버터가 JSON이나 XML 데이터를 파싱하여 변수에 담아준다. 

​

* JSON의 키값과 변수명을 다르게 이용하기 위해서는 

data class ItemModel(
    ...
    @SerializedName("id")
    new_id: String
)
Retrofit 객체 생성

  object RequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://13.209.144.115:3333")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInterface = retrofit.create(RequestInterface::class.java)
}
baseUrl() 함수로 서버 Url을 한 번 지정해놓으면 실제 네트워킹 때에는 이 뒤의 경로나 질의 문자열 등만 지정하면 된다.

baseUrl 설정되었더라도 실제 네트워킹 때는 다른 url을 사용할 수도 있음

​

addConverterFactory() : 서버와 통신할 데이터 타입에 맞는 컨버터. 위에서는 jSON 데이터를 Gson 라이브러리로 파싱

​

싱글톤 객체 (한번에 하나의 객체만 만드는)를 이용한다. 

Service interface

서버 네트워킹을 위한 함수를 인터페이스의 추상함수로 만드는 과정.

함수에 annotation을 이용하여 GET/POST 의 HTTP method 등을 지정한다.

이제 이 정보에 맞게 Call 객체를 자동으로 retrofit에서 만들어 줄거다. 

interface RequestInterface {
    @POST("/user/signin")
    fun requestLogin(@Body body: RequestLogin) :Call<ResponseLogin>
}
POST 방식으로 연동을 할 것이며 괄호 안에는 baseUrl 뒤의 path.

​

Call 객체 획득

​

main 함수

       val requestToServer=RequestToServer
        var id:String="kiansw"
        var pw:String="hello"
        requestToServer.service.requestLogin( // service
            RequestLogin(
                id=id,
                password = pw
            ) 
        ).enqueue(object : Callback<ResponseLogin>{// enqueue: 네트워킹 시도
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.isSuccessful) {//statusCode가 200~300사이일때 응답 body 이용 가능
                    if(response.body()!!.success){ //response login의 success 가 true 인경우
                        Toast.makeText(this@Login, "로그인 성공", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(this@Login, "아이디 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })//Callback 등록, import retrofit call back
응답 data는 response.body()에 위치한다.

​

​

​

Call<Type> 비동기적으로 Type을 받아오는 객체

Callback<Type> 동기적. Type객체 받아왔을 때 개발자가 할 행동
