package com.bcare.bcareapp.ui.quiz

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcare.bcareapp.R

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
    }

    companion object {
        const val EXTRA_TOKEN = "extra_token"
    }
}