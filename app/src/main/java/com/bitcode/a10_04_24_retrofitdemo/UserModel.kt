package com.bitcode.a10_04_24_retrofitdemo

import com.google.gson.annotations.SerializedName

data class UserModel(
    val data : Data1
)

data class Data1(
    val id : Int,
    val email : String,

    @SerializedName("first_name")
    val firstName : String,

    @SerializedName("last_name")
    val lastName : String,
    val avatar : String
)
