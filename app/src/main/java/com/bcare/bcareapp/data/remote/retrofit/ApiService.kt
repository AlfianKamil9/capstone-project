package com.bcare.bcareapp.data.remote.retrofit

import com.bcare.bcareapp.data.remote.response.SubmitQuiz.SubmitQuizResponse
import com.bcare.bcareapp.data.remote.response.artikel.ArtikelResponse
import com.bcare.bcareapp.data.remote.response.artikelDetail.DetailArtikelResponse
import com.bcare.bcareapp.data.remote.response.scan.ScanResponse
import com.bcare.bcareapp.data.remote.response.login.LoginResponse
import com.bcare.bcareapp.data.remote.response.logout.LogoutResponse
import com.bcare.bcareapp.data.remote.response.quiz.ShowQuizResponse
import com.bcare.bcareapp.data.remote.response.register.RegisterResponse
import com.bcare.bcareapp.data.remote.response.user.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    @Multipart
    @POST("api/v1/submit-image")
    suspend fun postSubmitImage(
        @Part image: MultipartBody.Part,
    ): ScanResponse


    @GET("/api/v1/questions")
    fun getQuiz(@Header("Authorization") token: String): Call<ShowQuizResponse>

    @GET("/api/v1/user")
    fun getUserData(@Header("Authorization") token: String): Call<UserResponse>

    @DELETE("/api/v1/logout")
    fun logout(@Header("Authorization") authorization: String): Call<LogoutResponse>

    @POST("/api/v1/submit-quiz")
    fun submitQuiz(
        @Header("Authorization") authorization: String,
        @Body requestBody: Map<String, String>
    ): Call<SubmitQuizResponse>
}

