package com.bcare.bcareapp.data.remote.response.SubmitImage

import com.google.gson.annotations.SerializedName

data class ResponseModel(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("saran")
    val saran: String,

    @field:SerializedName("data")
    val data: String,

    @field:SerializedName("message")
    val message: String
)
