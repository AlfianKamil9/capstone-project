package com.bcare.bcareapp.ui.welcome

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bcare.bcareapp.data.local.preference.UserPreference
import com.bcare.bcareapp.databinding.ActivityWelcomeBinding
import com.bcare.bcareapp.ui.login.LoginActivity
import com.bcare.bcareapp.ui.main.MainActivity
import com.bcare.bcareapp.ui.signup.SignupActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokenDataStore")

    //    private val loginViewModel: LoginViewModel by viewModels {
//        ViewModelFactory.get
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = UserPreference.getInstance(dataStore)
        CoroutineScope(Dispatchers.Main).launch {
            preferences.getToken().collect {
                if (it == "") {
                    binding = ActivityWelcomeBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    setupAction()
                    playAnimation()
                    playAlphaAnimation()
                    supportActionBar?.hide()
                } else {
                    val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                    intent.putExtra(MainActivity.EXTRA_TOKEN, it)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }


    companion object {
        private const val TAG = "LoginActivity"
    }

    private fun setupAction() {
        binding.btnLoginWelcome.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnSignupWelcome.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivWelcome, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

    private fun playAlphaAnimation() {
        ObjectAnimator.ofFloat(binding.ivWelcome, View.ALPHA, 0f, 1f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }
}