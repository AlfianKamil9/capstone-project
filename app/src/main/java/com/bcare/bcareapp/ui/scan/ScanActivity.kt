package com.bcare.bcareapp.ui.scan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.local.preference.UserPreference
//import com.bcare.bcareapp.data.remote.response.SubmitImage.ResponseModel
import com.bcare.bcareapp.data.remote.retrofit.ApiConfig
import com.bcare.bcareapp.data.remote.retrofit.ApiService
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ScanActivity : AppCompatActivity() {
//
//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tokeDataStore")
//    private val preferences by lazy {
//        UserPreference.getInstance(this@ScanActivity.dataStore)
//    }
//
////    private val apiService: ApiService by lazy {
////        Retrofit.Builder()
////            .baseUrl("http://34.128.78.237:3000/")
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////            .create(ApiService::class.java)
////    }
//
//    private lateinit var apiService: ApiService
//
//    private val CAMERA_PERMISSION_REQUEST_CODE = 1001
//    private var capturedImageFile: File? = null
//    private var imageFile: File? = null
//
//    private val takePictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                // Handle logic for successful image capture
//                capturedImageFile?.let { file ->
//                    showCapturedImage(file)
//                    imageFile = file // Assign the value to the class variable
//                }
//            } else {
//                Toast.makeText(this, "Image capture failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//
//    private val btnUpload: Button by lazy { findViewById(R.id.btnUpload) }
//    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBarScan) }
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
//
//        apiService = ApiConfig.getApi()
//
//        val btnCamera: Button = findViewById(R.id.btnCamera)
//
//        btnCamera.setOnClickListener {
//            checkCameraPermission()
//        }
//
//        btnUpload.setOnClickListener {
//            if (capturedImageFile != null) {
//                handleImageUpload()
//            } else {
//                Toast.makeText(this, "Capture an image first", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        lifecycleScope.launchWhenStarted {
//            preferences.getToken().collect { token ->
//                if (token.isNotEmpty()) {
//                    // Use the safe call operator (?.) to handle nullable imageFile
//                    imageFile?.let { file ->
//                        submitImage(file, token)
//                    } ?: run {
//                        Toast.makeText(this@ScanActivity, "No image to submit", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//
    }
//
//    private fun handleImageUpload() {
//        lifecycleScope.launch {
//            preferences.getToken().collect { token ->
//                if (token.isNotEmpty()) {
//                    // Show the progress bar while uploading
//                    progressBar.visibility = View.VISIBLE
//
//                    // Upload the captured image
//                    submitImage(capturedImageFile!!, token)
//
//                    // Hide the progress bar after uploading
//                    progressBar.visibility = View.GONE
//                } else {
//                    Toast.makeText(this@ScanActivity, "Token is empty", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun checkCameraPermission() {
//        if (ContextCompat.checkSelfPermission(
//                this,
//                "android.permission.CAMERA"
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Create a temporary file to store the captured image
//            capturedImageFile = createTempImageFile()
//            capturedImageFile?.let { file ->
//                val photoUri = FileProvider.getUriForFile(
//                    this,
//                    "com.bcare.bcareapp", // Replace with your app's package name
//                    file
//                )
//
//                takePictureLauncher.launch(
//                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
//                        putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
//                    }
//                )
//            }
//        } else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf("android.permission.CAMERA"),
//                CAMERA_PERMISSION_REQUEST_CODE
//            )
//        }
//    }
//
//    private fun createTempImageFile(): File? {
//        return try {
//            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//            val imageFileName = "JPEG_${timeStamp}_"
//            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//            File.createTempFile(
//                imageFileName,
//                ".jpg",
//                storageDir
//            )
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//            null
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                takePictureLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
//            } else {
//                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun showCapturedImage(file: File) {
//        val imgUpload: ImageView = findViewById(R.id.ImgUpload)
//        imgUpload.setImageURI(Uri.fromFile(file))
//        capturedImageFile = file
//    }
//
//    private fun showImage(file: File) {
//        val imgUpload: ImageView = findViewById(R.id.ImgResult)
//        imgUpload.setImageURI(Uri.fromFile(file))
//        capturedImageFile = file
//    }
//
//    private suspend fun submitImage(imageFile: File, token: String) {
//        val filePart = MultipartBody.Part.createFormData(
//            "file",
//            imageFile.name,
//            imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//        )
//
//
//        try {
//            val response = apiService.submitImage(filePart, "Bearer $token")
//
//            if (response.isSuccessful) {
//                val responseBody = response.body()
//                updateUIWithResponse(responseBody)
//            } else {
//                val errorBody = response.errorBody()?.string()
//                val errorMessage = if (errorBody.isNullOrEmpty()) {
//                    "Image upload failed"
//                } else {
//                    errorBody
//                }
//                Toast.makeText(this@ScanActivity, errorMessage, Toast.LENGTH_SHORT).show()
//            }
//        } catch (e: Exception) {
//            Toast.makeText(this@ScanActivity, "Network error", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun updateUIWithResponse(responseBody: ResponseModel?) {
//        val scrollView: ScrollView = findViewById(R.id.scrollView)
//        val scrollViewResult: ScrollView = findViewById(R.id.scrollViewResult)
//
//        val txtResult: TextView = findViewById(R.id.txtResult)
//        val tvResult: TextView = findViewById(R.id.tvResult)
//        val tvSaran: TextView = findViewById(R.id.tvSuggestion)
//        val imageResult: ImageView = findViewById(R.id.ImgResult)
//
//        if (responseBody != null) {
//            // Update UI elements with response data
//            txtResult.text = responseBody.message
//            tvResult.text = responseBody.data
//            tvSaran.text = responseBody.saran
//
//            capturedImageFile?.let { file ->
//                showImage(file)
//                imageFile = file // Assign the value to the class variable
//            }
//
//            // Make the ScrollView invisible and ScrollViewResult visible
//            scrollView.visibility = View.GONE
//            scrollViewResult.visibility = View.VISIBLE
//        } else {
//            // If there's no response data, hide ScrollViewResult and show ScrollView
//            scrollViewResult.visibility = View.GONE
//            scrollView.visibility = View.VISIBLE
//        }
//    }
//
//
//    private companion object {
//        const val CAMERA_PERMISSION_REQUEST_CODE = 1
//    }
}