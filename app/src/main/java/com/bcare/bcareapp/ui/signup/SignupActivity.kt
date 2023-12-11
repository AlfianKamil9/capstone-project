package com.bcare.bcareapp.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.local.result.Result
import com.bcare.bcareapp.data.remote.response.register.RegisterResponse
import com.bcare.bcareapp.databinding.ActivitySignUpBinding
import com.bcare.bcareapp.ui.ViewModelFactory
import com.bcare.bcareapp.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signupViewModel: SignupViewModel by viewModels {
        ViewModelFactory(applicationContext)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupAction()
    }
    private fun processRegister(body: RegisterResponse) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.success))
                setMessage(getString(R.string.register_success))
                setPositiveButton(getString(R.string.login)) { _, _ ->
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }

    }

    private fun setupAction() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etUsernameSignup.text.toString()
            val email = binding.etEmailSignup.text.toString()
            val familyEmail = binding.etFamilyEmailSignup.text.toString()
            val password = binding.etPasswordSignup.text.toString()
            val confPassword = binding.etConfirmPasswordSignup.text.toString()

            signupViewModel.signup(email, name, familyEmail, password, confPassword).observe(this) {
                if (it != null ) {
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)
                            processRegister(it.data)
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
        binding.progressBarSignup.visibility = if (bool) View.VISIBLE else View.GONE
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}