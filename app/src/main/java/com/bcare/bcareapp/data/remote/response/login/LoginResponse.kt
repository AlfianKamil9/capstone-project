package com.bcare.bcareapp.data.remote.response.login

import com.google.gson.annotations.SerializedName

class LoginResponse (
    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("token")
    val token: String
)
