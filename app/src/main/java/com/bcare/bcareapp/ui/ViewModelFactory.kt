package com.bcare.bcareapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bcare.bcareapp.data.di.Injection
import com.bcare.bcareapp.data.local.repository.UserRepository
import com.bcare.bcareapp.ui.login.LoginViewModel
import com.bcare.bcareapp.ui.signup.SignupViewModel


class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(Injection.provideRepository(context)) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideRepository(context)) as T
            }

//            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                MainViewModel(Injection.provideRepository(context)) as T
//            }
//
//            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
//                AddStoryViewModel(Injection.provideRepository(context)) as T
//            }
//
//            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
//                MapsViewModel(Injection.provideRepository(context)) as T
//            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

}