package com.bcare.bcareapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.local.preference.UserPreference
import com.bcare.bcareapp.data.local.result.Result
import com.bcare.bcareapp.data.remote.response.login.LoginResponse
import com.bcare.bcareapp.databinding.ActivityLoginBinding
import com.bcare.bcareapp.ui.ViewModelFactory
import com.bcare.bcareapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(applicationContext)
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokenDataStore")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        supportActionBar?.hide()
    }

    private fun processLogin(body: LoginResponse) {
        try {
            if (body.error) {
                showSnackbar(body.message)
            } else {
                val preferences = UserPreference.getInstance(dataStore)
                AlertDialog.Builder(this).apply {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra(MainActivity.EXTRA_TOKEN, body.loginResult.token)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    CoroutineScope(Dispatchers.Main).launch {
                        preferences.saveToken(body.loginResult.token)
                    }
                    finish()
                    create()
                    show()
                }
            }
        } catch (e: HttpException) {
            // Handle the HTTP exception by showing an error message
            val errorMessage = "An error occurred: ${e.message}"
            showSnackbar(errorMessage)
        }
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()

            loginViewModel.login(email, password).observe(this) {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            processLogin(it.data)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            showSnackbar(it.error)
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(bool: Boolean) {
        binding.progressBarLogin.visibility = if (bool) View.VISIBLE else View.GONE
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}