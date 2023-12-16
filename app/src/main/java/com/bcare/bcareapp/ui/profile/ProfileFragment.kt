package com.bcare.bcareapp.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.local.preference.UserPreference
import com.bcare.bcareapp.data.remote.response.user.UserData
import com.bcare.bcareapp.data.remote.response.user.UserResponse
import com.bcare.bcareapp.data.remote.retrofit.ApiConfig
import com.bcare.bcareapp.data.remote.retrofit.ApiService
import com.bcare.bcareapp.ui.about.AboutActivity
import com.bcare.bcareapp.ui.welcome.WelcomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokeDataStore")

class ProfileFragment : Fragment() {
    private lateinit var apiService: ApiService

    private lateinit var tvUsernameProfile: TextView
    private lateinit var tvEmailProfile: TextView
    private lateinit var tvFamilyEmailProfile: TextView

    private val preferences by lazy {
        UserPreference.getInstance(requireContext().dataStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvUsernameProfile = view.findViewById(R.id.tvUsernameProfile)
        tvEmailProfile = view.findViewById(R.id.tvEmailProfile)
        tvFamilyEmailProfile = view.findViewById(R.id.tvFamilyEmailProfile)


        apiService = ApiConfig.getApi()

        lifecycleScope.launchWhenStarted {
            preferences.getToken().collect { token ->
                if (token.isNotEmpty()) {
                    getUserData(token)

                    // btn Logout
                    val btnLogout: Button = view.findViewById(R.id.btnLogoutProfile)
                    btnLogout.setOnClickListener { logout(token) }
                }
            }
        }

        val btnAbout: Button = view.findViewById(R.id.btnAboutappProfile)
        btnAbout.setOnClickListener { openAboutActivity(view) }
    }

    fun openAboutActivity(view: View) {
        val intent = Intent(requireContext(), AboutActivity::class.java)
        startActivity(intent)
    }

    private fun getUserData(token: String) {
        val call = apiService.getUserData("Bearer $token")

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    updateUI(user)
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody.isNullOrEmpty()) {
                        "Failed to fetch user data"
                    } else {
                        errorBody
                    }

                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI(user: UserData?) {
        user?.let {
            tvUsernameProfile.text = it.name
            tvEmailProfile.text = it.email
            tvFamilyEmailProfile.text = it.familyEmail
        }
    }


    private fun logout(token: String) {
        lifecycleScope.launchWhenStarted {
            val call = apiService.logout("Bearer $token")

            try {
                val response = call.awaitResponse()

                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Logout successful", Toast.LENGTH_SHORT).show()

                    // Reset token in dataStore
                    preferences.saveToken("")

                    // Intent WelcomeActivity
                    val intent = Intent(requireContext(), WelcomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()

                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody.isNullOrEmpty()) {
                        "Failed to logout"
                    } else {
                        errorBody
                    }

                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
