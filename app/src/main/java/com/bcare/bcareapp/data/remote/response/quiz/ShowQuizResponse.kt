package com.bcare.bcareapp.data.remote.response.quiz

import com.google.gson.annotations.SerializedName

data class ShowQuizResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String
)

data class DataItem(

	@field:SerializedName("opsi_2")
	val opsi2: String,

	@field:SerializedName("opsi_3")
	val opsi3: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("opsi_1")
	val opsi1: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("opsi_4")
	val opsi4: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
