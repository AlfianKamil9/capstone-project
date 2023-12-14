package com.bcare.bcareapp.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: UserData,

	@field:SerializedName("message")
	val message: String
)

data class UserData(

	@field:SerializedName("familyEmail")
	val familyEmail: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String
)
