package com.example.soptseminar3.kakaoBook

import com.google.gson.annotations.SerializedName
import java.util.*

data class BookData(
    var imageurl:String,
    var title:String,
    var content:String,
    var author:String
)

data class RequestBookData(
    var query:String
)
data class ResponseBookData(
    var meta:Meta?,
    var documents:DocumentData?
)

data class Meta(
    val total_count:Int,
    val pageable_count:Int,
    val is_end:Boolean
)

data class DocumentData(
    val title:String,
    val contents:String,
    val url:String,
    val authors:ArrayList<String>,
    @SerializedName("thumbnail")
    val image:String
)

