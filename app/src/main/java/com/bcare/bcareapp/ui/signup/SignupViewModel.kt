package com.bcare.bcareapp.ui.signup

import androidx.lifecycle.ViewModel
import com.bcare.bcareapp.data.local.repository.UserRepository

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun signup(email: String, name: String, familyEmail: String, password: String, confPassword: String) =
        userRepository.postRegister(email, name, familyEmail, password, confPassword)
}