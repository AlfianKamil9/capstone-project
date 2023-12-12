package com.bcare.bcareapp.ui.home

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bcare.bcareapp.data.di.Injection
import com.bcare.bcareapp.data.local.repository.ArtikelRepository

class ArtikelViewModelFactory(
    private val articleRepository: ArtikelRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ArtikelViewModel::class.java) -> {
                ArtikelViewModel(articleRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ArtikelViewModelFactory? = null
        fun getInstance(
            context: Context,
            dataStore: DataStore<Preferences>
        ): ArtikelViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ArtikelViewModelFactory(
                    Injection.provideArticleRepository()
                )
            }.also { instance = it }
    }
}