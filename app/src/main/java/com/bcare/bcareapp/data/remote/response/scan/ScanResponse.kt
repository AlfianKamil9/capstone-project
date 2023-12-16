package com.bcare.bcareapp.data.remote.response.scan

import com.google.gson.annotations.SerializedName

class ScanResponse (
    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: String

)


