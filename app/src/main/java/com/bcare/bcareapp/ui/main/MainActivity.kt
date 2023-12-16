package com.bcare.bcareapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bcare.bcareapp.R
import com.bcare.bcareapp.databinding.ActivityMainBinding
import com.bcare.bcareapp.ui.ViewModelFactory
import com.bcare.bcareapp.ui.quiz.QuizActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(applicationContext)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokeDataStore")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFab()

        //ini untuk action bar
        supportActionBar?.hide()
        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
            setLogo(R.drawable.tulisan)
            setDisplayUseLogoEnabled(true)
        }

        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        //menghapus background transparant
        navView.background = null

        navView.menu.findItem(R.id.navigation_empty).isEnabled = false
    }

    private fun setupFab() {
        binding.fabScan.setOnClickListener {
            navigateToQuizPage()
        }
    }

    //Untuk Button Scan menuju quiz
    private fun navigateToQuizPage() {
        val token = intent.getStringExtra(EXTRA_TOKEN)
        val intent = Intent(this@MainActivity, QuizActivity::class.java)
        intent.putExtra(QuizActivity.EXTRA_TOKEN, token)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_TOKEN = "extra_token"
    }
}