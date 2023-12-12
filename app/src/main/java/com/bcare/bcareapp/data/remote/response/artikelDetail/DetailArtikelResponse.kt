package com.bcare.bcareapp.data.remote.response.artikelDetail

import com.google.gson.annotations.SerializedName

data class DetailArtikelResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("subTitle")
	val subTitle: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
