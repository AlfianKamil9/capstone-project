package com.bcare.bcareapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.local.preference.UserPreference
import com.bcare.bcareapp.databinding.FragmentProfileBinding
import com.bcare.bcareapp.ui.about.AboutActivity
import com.bcare.bcareapp.ui.welcome.WelcomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private lateinit var dataStore: DataStore<Preferences>

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()

        val btnAbout: Button = view.findViewById(R.id.btnAboutappProfile)
        btnAbout.setOnClickListener { openAboutActivity(view) }
    }

    fun openAboutActivity(view: View) {
        val intent = Intent(requireContext(), AboutActivity::class.java)
        startActivity(intent)
    }

    private fun setupAction() {
        binding.apply {
            btnLogoutProfile.setOnClickListener{
                logout()
            }
            btnAboutappProfile.setOnClickListener{
                aboutActivity()
            }
        }
    }

    private fun logout() {
        val preferences = UserPreference.getInstance(dataStore)
        binding.btnLogoutProfile.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                preferences.saveToken("")
            }

            val intent = Intent(requireActivity(), WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun aboutActivity() {
        val intent = Intent(requireActivity(), AboutActivity::class.java)
        startActivity(intent)
    }
}