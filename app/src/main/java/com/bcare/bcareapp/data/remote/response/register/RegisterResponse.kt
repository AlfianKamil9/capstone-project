package com.bcare.bcareapp.data.remote.response.register

import com.google.gson.annotations.SerializedName

class RegisterResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
)