package com.bcare.bcareapp.ui.login

import androidx.lifecycle.ViewModel
import com.bcare.bcareapp.data.local.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {
    fun login(email: String, password: String) =
        userRepository.postLogin(email, password)

}