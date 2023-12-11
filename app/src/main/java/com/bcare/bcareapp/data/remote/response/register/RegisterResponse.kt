package com.bcare.bcareapp.data.remote.response.register

import com.google.gson.annotations.SerializedName

class RegisterResponse (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
)