package com.bcare.bcareapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bcare.bcareapp.R
import com.bcare.bcareapp.databinding.ActivityMainBinding
import com.bcare.bcareapp.ui.quiz.QuizActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var fabScan: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        fabScan = binding.fabScan
        fabScan.setOnClickListener {
            navigateToQuizPage()
        }

        navView.menu.findItem(R.id.navigation_empty).isEnabled = false
    }

    //Untuk Button Scan menuju quiz
    private fun navigateToQuizPage() {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }
}