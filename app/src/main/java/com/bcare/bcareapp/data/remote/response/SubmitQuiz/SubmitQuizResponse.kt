package com.bcare.bcareapp.data.remote.response.SubmitQuiz

import com.google.gson.annotations.SerializedName

data class SubmitQuizResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String
)
