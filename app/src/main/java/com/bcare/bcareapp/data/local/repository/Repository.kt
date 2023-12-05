package com.bcare.bcareapp.data.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bcare.bcareapp.data.local.result.Result
import com.bcare.bcareapp.data.remote.response.login.LoginResponse
import com.bcare.bcareapp.data.remote.response.register.RegisterResponse
import com.bcare.bcareapp.data.remote.retrofit.ApiService

class Repository private constructor(
    private val apiService: ApiService){


    fun userLogin(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        try {
            val client = apiService.postLogin(email, password)
            emit(Result.Success(client))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "userLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun userRegister(
        email: String,
        name: String,
        familyName: String,
        password: String,
        confPassword: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        try {
            val client = apiService.postRegister(email, name, familyName, password, confPassword)
            emit(Result.Success(client))
        } catch (e: Exception) {
            Log.e("RegisterViewModel", "userRegister: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

}