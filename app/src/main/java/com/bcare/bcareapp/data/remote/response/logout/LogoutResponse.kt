package com.bcare.bcareapp.data.remote.response.logout

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String
)
