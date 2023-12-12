package com.bcare.bcareapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bcare.bcareapp.data.local.repository.ArtikelRepository

class ArtikelViewModel(private val articleRepository: ArtikelRepository) : ViewModel() {
    fun getListArticle() = articleRepository.getListArticle().asLiveData()

    fun getDetailArticle(id:Int) = articleRepository.getDetailArticle(id).asLiveData()

    companion object {
        private const val TAG = "ArtikelViewModel"
    }
}