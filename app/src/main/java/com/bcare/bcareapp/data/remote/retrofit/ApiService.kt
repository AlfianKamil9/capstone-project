package com.bcare.bcareapp.data.remote.retrofit

import com.bcare.bcareapp.data.remote.response.artikel.ArtikelResponse
import com.bcare.bcareapp.data.remote.response.artikelDetail.DetailArtikelResponse
import com.bcare.bcareapp.data.remote.response.login.LoginResponse
import com.bcare.bcareapp.data.remote.response.register.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("api/v1/login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("/api/v1/register")
    suspend fun postRegister(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("familyEmail") familyEmail : String,
        @Field("password") password: String,
        @Field("confPassword") confPassword : String,
    ): RegisterResponse


    @GET("/api/v1/artikel")
    suspend fun getAllArticle(): ArtikelResponse

    @GET("/api/v1/artikel/{id}")
    suspend fun getDetailArticle(
        @Path("id") id: Int
    ): DetailArtikelResponse
}

