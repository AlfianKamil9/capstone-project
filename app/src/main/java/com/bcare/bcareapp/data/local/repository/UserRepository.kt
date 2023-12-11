package com.bcare.bcareapp.data.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bcare.bcareapp.data.local.result.Result
import com.bcare.bcareapp.data.remote.response.login.LoginResponse
import com.bcare.bcareapp.data.remote.response.register.RegisterResponse
import com.bcare.bcareapp.data.remote.retrofit.ApiService

class UserRepository(
    private val apiService: ApiService) {

    fun postLogin(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.postLogin(email, password)
            emit(Result.Success(client))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "userLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postRegister(
        email: String,
        name: String,
        familyEmail: String,
        password: String,
        confPassword: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        try {
            val response = apiService.postRegister(email, name, familyEmail, password, confPassword)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "userRegister: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

//    fun  userLogout(): Flow<Result<String>> = flow {
//        emit(Result.Loading)
//        userPreference.logout()
//        emit(Result.Success("Success"))
//    }

//    fun getDataUser(
//        token = String
//    ): Flow<Result<Data>

//    companion object {
//        private var instance: UserRepository? = null
//        fun getInstace(
//            apiService: ApiService, userPreference: UserPreference
//        ): UserRepository = instance ?: synchronized(this) {
//            instance ?: UserRepository(apiService, userPreference)
//        }.also { instance = it }
//    }

}