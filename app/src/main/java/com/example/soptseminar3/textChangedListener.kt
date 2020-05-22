package com.example.soptseminar3

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

//함수를 매개변수로 넘기기
fun EditText.textChangedListener(textChanged :(CharSequence?)->Unit){
    this.addTextChangedListener(object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            Unit
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Unit
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textChanged(s)
        }
    })
}