package com.bcare.bcareapp.data.remote.retrofit

import com.bcare.bcareapp.data.remote.response.login.LoginResponse
import com.bcare.bcareapp.data.remote.response.register.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
        @Field("password") password: String,
        @Field("familyEmail") familyEmail : String,
        @Field("confPassword") confPassword : String,
    ): RegisterResponse


}

