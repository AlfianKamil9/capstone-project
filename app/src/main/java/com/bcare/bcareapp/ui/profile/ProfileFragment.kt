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
import androidx.navigation.fragment.NavHostFragment
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.local.preference.UserPreference
import com.bcare.bcareapp.data.remote.response.logout.LogoutResponse
import com.bcare.bcareapp.data.remote.response.user.UserData
import com.bcare.bcareapp.data.remote.response.user.UserResponse
import com.bcare.bcareapp.data.remote.retrofit.ApiConfig
import com.bcare.bcareapp.data.remote.retrofit.ApiService
import com.bcare.bcareapp.ui.welcome.WelcomeActivity
import kotlinx.coroutines.flow.first
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokeDataStore")

class ProfileFragment : Fragment() {
//    private val baseUrl = "http://34.128.78.237:3000/"
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

        // Initialize TextViews
        tvUsernameProfile = view.findViewById(R.id.tvUsernameProfile)
        tvEmailProfile = view.findViewById(R.id.tvEmailProfile)
        tvFamilyEmailProfile = view.findViewById(R.id.tvFamilyEmailProfile)

//        // Initialize Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

        apiService = ApiConfig.getApi()

        // Retrieve token from UserPreference
        lifecycleScope.launchWhenStarted {
            preferences.getToken().collect { token ->
                // Make API request only if the token is not empty
                if (token.isNotEmpty()) {
                    getUserData(token)

                    // btn Logout
                    val btnLogout: Button = view.findViewById(R.id.btnLogoutProfile)
                    btnLogout.setOnClickListener { logout(token) }
                }
            }
        }
    }

    private fun getUserData(token: String) {
        // Make API request
        val call = apiService.getUserData("Bearer $token")

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    updateUI(user)
                } else {
                    // Handle unsuccessful response
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
                // Handle failure
                Toast.makeText(requireContext(), "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI(user: UserData?) {
        // Update UI components with user data
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
                    // Handle successful logout
                    Toast.makeText(requireContext(), "Logout successful", Toast.LENGTH_SHORT).show()

                    // Reset token in dataStore within the coroutine
                    preferences.saveToken("")

                    // Start WelcomeActivity
                    val intent = Intent(requireContext(), WelcomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()

                } else {
                    // Handle unsuccessful logout
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody.isNullOrEmpty()) {
                        "Failed to logout"
                    } else {
                        errorBody
                    }

                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Handle exceptions
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
