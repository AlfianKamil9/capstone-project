package com.bcare.bcareapp.ui.artikel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.remote.response.artikelDetail.Data
import com.bcare.bcareapp.databinding.ActivityArtikelBinding
import com.bcare.bcareapp.ui.home.ArtikelViewModel
import com.bcare.bcareapp.ui.home.ArtikelViewModelFactory
import com.bumptech.glide.Glide
import com.bcare.bcareapp.data.local.result.Result

class ArtikelActivity : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private lateinit var binding: ActivityArtikelBinding
    private val articleViewModel: ArtikelViewModel by viewModels {
        ArtikelViewModelFactory.getInstance(this, dataStore)
    }

    private var id: Int = 0
    private var detailArticle: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        id = intent.getIntExtra(ID_ARTICLE, 0)

        articleViewModel.getDetailArticle(id = id).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    detailArticle = result.data
                    setDataArticle(detailArticle!!)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Failed to load article",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun setDataArticle(detailArticle: Data) {
        binding.apply {
            Glide.with(root)
                .load(detailArticle.image)
                .placeholder(R.drawable.artikelimage)
                .into(ivArtikelDetail)
            tvJudulArtikelDetail.text = detailArticle.title
            tvIsiArtikel.text = detailArticle.content
        }
    }

    companion object {
        const val ID_ARTICLE = "id"
    }

}