package com.bcare.bcareapp.ui.quiz

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.bcare.bcareapp.R
import com.bcare.bcareapp.data.remote.response.quiz.ShowQuizResponse
import com.bcare.bcareapp.data.remote.retrofit.ApiConfig
import com.bcare.bcareapp.data.remote.retrofit.ApiService
import com.bcare.bcareapp.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizActivity : AppCompatActivity() {
    // Assume you have a variable to hold the quiz response
    private lateinit var quizResponse: ShowQuizResponse

    // RadioButton Soal 1
    private lateinit var aSoal1: RadioButton
    private lateinit var bSoal1: RadioButton
    private lateinit var cSoal1: RadioButton
    private lateinit var dSoal1: RadioButton
    // RadioButton Soal 2
    private lateinit var aSoal2: RadioButton
    private lateinit var bSoal2: RadioButton
    private lateinit var cSoal2: RadioButton
    private lateinit var dSoal2: RadioButton
    // RadioButton Soal 3
    private lateinit var aSoal3: RadioButton
    private lateinit var bSoal3: RadioButton
    private lateinit var cSoal3: RadioButton
    private lateinit var dSoal3: RadioButton
    // RadioButton Soal 4
    private lateinit var aSoal4: RadioButton
    private lateinit var bSoal4: RadioButton
    private lateinit var cSoal4: RadioButton
    private lateinit var dSoal4: RadioButton
    // RadioButton Soal 5
    private lateinit var aSoal5: RadioButton
    private lateinit var bSoal5: RadioButton
    private lateinit var cSoal5: RadioButton
    private lateinit var dSoal5: RadioButton
    // RadioButton Soal 6
    private lateinit var aSoal6: RadioButton
    private lateinit var bSoal6: RadioButton
    private lateinit var cSoal6: RadioButton
    private lateinit var dSoal6: RadioButton
    // RadioButton Soal 7
    private lateinit var aSoal7: RadioButton
    private lateinit var bSoal7: RadioButton
    private lateinit var cSoal7: RadioButton
    private lateinit var dSoal7: RadioButton
    // RadioButton Soal 8
    private lateinit var aSoal8: RadioButton
    private lateinit var bSoal8: RadioButton
    private lateinit var cSoal8: RadioButton
    private lateinit var dSoal8: RadioButton
    // RadioButton Soal 9
    private lateinit var aSoal9: RadioButton
    private lateinit var bSoal9: RadioButton
    private lateinit var cSoal9: RadioButton
    private lateinit var dSoal9: RadioButton
    // RadioButton Soal 10
    private lateinit var aSoal10: RadioButton
    private lateinit var bSoal10: RadioButton
    private lateinit var cSoal10: RadioButton
    private lateinit var dSoal10: RadioButton

    // Text View
    private lateinit var tvSoal1: TextView
    private lateinit var tvSoal2: TextView
    private lateinit var tvSoal3: TextView
    private lateinit var tvSoal4: TextView
    private lateinit var tvSoal5: TextView
    private lateinit var tvSoal6: TextView
    private lateinit var tvSoal7: TextView
    private lateinit var tvSoal8: TextView
    private lateinit var tvSoal9: TextView
    private lateinit var tvSoal10: TextView

    private lateinit var btnScanFace: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.128.78.237:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the QuizApi interface
        val quizApi = retrofit.create(ApiService::class.java)

        val token = retrieveTokenFromSharedPreferences()
        val call = quizApi.getQuiz("Bearer $token")
        call.enqueue(object : Callback<ShowQuizResponse> {
            override fun onResponse(call: Call<ShowQuizResponse>, response: Response<ShowQuizResponse>) {
                if (response.isSuccessful) {
                    // Quiz data is successfully received
                    quizResponse = response.body()!!

                    // Save the token to SharedPreferences
                    saveTokenToSharedPreferences(token)

                    // set the data to UI elements
                    setQuizDataToUI()

                } else {
                    // Handle unsuccessful response
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody.isNullOrEmpty()) {
                        "Failed to fetch quiz data"
                    } else {
                        errorBody
                    }

                    Toast.makeText(this@QuizActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ShowQuizResponse>, t: Throwable) {
                // Handle network failure
                Toast.makeText(this@QuizActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        // Initialize RadioButtons soal 1
        aSoal1 = findViewById(R.id.a_soal1)
        bSoal1 = findViewById(R.id.b_soal1)
        cSoal1 = findViewById(R.id.c_soal1)
        dSoal1 = findViewById(R.id.d_soal1)
        // Initialize RadioButtons soal 2
        aSoal2 = findViewById(R.id.a_soal2)
        bSoal2 = findViewById(R.id.b_soal2)
        cSoal2 = findViewById(R.id.c_soal2)
        dSoal2 = findViewById(R.id.d_soal2)
        // Initialize RadioButtons soal 3
        aSoal3 = findViewById(R.id.a_soal3)
        bSoal3 = findViewById(R.id.b_soal3)
        cSoal3 = findViewById(R.id.c_soal3)
        dSoal3 = findViewById(R.id.d_soal3)
        // Initialize RadioButtons soal 4
        aSoal4 = findViewById(R.id.a_soal4)
        bSoal4 = findViewById(R.id.b_soal4)
        cSoal4 = findViewById(R.id.c_soal4)
        dSoal4 = findViewById(R.id.d_soal4)
        // Initialize RadioButtons soal 5
        aSoal5 = findViewById(R.id.a_soal5)
        bSoal5 = findViewById(R.id.b_soal5)
        cSoal5 = findViewById(R.id.c_soal5)
        dSoal5 = findViewById(R.id.d_soal5)
        // Initialize RadioButtons soal 6
        aSoal6 = findViewById(R.id.a_soal6)
        bSoal6 = findViewById(R.id.b_soal6)
        cSoal6 = findViewById(R.id.c_soal6)
        dSoal6 = findViewById(R.id.d_soal6)
        // Initialize RadioButtons soal 7
        aSoal7 = findViewById(R.id.a_soal7)
        bSoal7 = findViewById(R.id.b_soal7)
        cSoal7 = findViewById(R.id.c_soal7)
        dSoal7 = findViewById(R.id.d_soal7)
        // Initialize RadioButtons soal 8
        aSoal8 = findViewById(R.id.a_soal8)
        bSoal8 = findViewById(R.id.b_soal8)
        cSoal8 = findViewById(R.id.c_soal8)
        dSoal8 = findViewById(R.id.d_soal8)
        // Initialize RadioButtons soal 9
        aSoal9 = findViewById(R.id.a_soal9)
        bSoal9 = findViewById(R.id.b_soal9)
        cSoal9 = findViewById(R.id.c_soal9)
        dSoal9 = findViewById(R.id.d_soal9)
        // Initialize RadioButtons soal 10
        aSoal10 = findViewById(R.id.a_soal10)
        bSoal10 = findViewById(R.id.b_soal10)
        cSoal10 = findViewById(R.id.c_soal10)
        dSoal10 = findViewById(R.id.d_soal10)

        // Initialize TextView
        tvSoal1 = findViewById(R.id.tvSoal1)
        tvSoal2 = findViewById(R.id.tvSoal2)
        tvSoal3 = findViewById(R.id.tvSoal3)
        tvSoal4 = findViewById(R.id.tvSoal4)
        tvSoal5 = findViewById(R.id.tvSoal5)
        tvSoal6 = findViewById(R.id.tvSoal6)
        tvSoal7 = findViewById(R.id.tvSoal7)
        tvSoal8 = findViewById(R.id.tvSoal8)
        tvSoal9 = findViewById(R.id.tvSoal9)
        tvSoal10 = findViewById(R.id.tvSoal10)


//        btnScanFace = findViewById(R.id.btnScanFace)
//
//        btnScanFace.setOnClickListener {
//            // Create the request body for the POST method
//            val requestBody = mapOf(
//                "a1" to getSelectedAnswer(aSoal1, bSoal1, cSoal1, dSoal1),
//                "a2" to getSelectedAnswer(aSoal2, bSoal2, cSoal2, dSoal2),
//                // Repeat the above pattern for the remaining questions
//            )
//
//            // Send the POST request with the created request body
//            // TODO: Implement the code to send the POST request
//        }

    }

    private fun saveTokenToSharedPreferences(token: String?) {
        val sharedPreferences = getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    private fun retrieveTokenFromSharedPreferences(): String? {
        val sharedPreferences = getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

    private fun setQuizDataToUI() {
        // Set the text Soal 1
        tvSoal1.text = quizResponse.data[0].question
        aSoal1.text = quizResponse.data[0].opsi1
        bSoal1.text = quizResponse.data[0].opsi2
        cSoal1.text = quizResponse.data[0].opsi3
        dSoal1.text = quizResponse.data[0].opsi4
        // Set the text Soal 2
        tvSoal2.text = quizResponse.data[1].question
        aSoal2.text = quizResponse.data[1].opsi1
        bSoal2.text = quizResponse.data[1].opsi2
        cSoal2.text = quizResponse.data[1].opsi3
        dSoal2.text = quizResponse.data[1].opsi4
        // Set the text Soal 3
        tvSoal3.text = quizResponse.data[2].question
        aSoal3.text = quizResponse.data[2].opsi1
        bSoal3.text = quizResponse.data[2].opsi2
        cSoal3.text = quizResponse.data[2].opsi3
        dSoal3.text = quizResponse.data[2].opsi4
        // Set the text Soal 4
        tvSoal4.text = quizResponse.data[3].question
        aSoal4.text = quizResponse.data[3].opsi1
        bSoal4.text = quizResponse.data[3].opsi2
        cSoal4.text = quizResponse.data[3].opsi3
        dSoal4.text = quizResponse.data[3].opsi4
        // Set the text Soal 5
        tvSoal5.text = quizResponse.data[4].question
        aSoal5.text = quizResponse.data[4].opsi1
        bSoal5.text = quizResponse.data[4].opsi2
        cSoal5.text = quizResponse.data[4].opsi3
        dSoal5.text = quizResponse.data[4].opsi4
        // Set the text Soal 6
        tvSoal6.text = quizResponse.data[5].question
        aSoal6.text = quizResponse.data[5].opsi1
        bSoal6.text = quizResponse.data[5].opsi2
        cSoal6.text = quizResponse.data[5].opsi3
        dSoal6.text = quizResponse.data[5].opsi4
        // Set the text Soal 7
        tvSoal7.text = quizResponse.data[6].question
        aSoal7.text = quizResponse.data[6].opsi1
        bSoal7.text = quizResponse.data[6].opsi2
        cSoal7.text = quizResponse.data[6].opsi3
        dSoal7.text = quizResponse.data[6].opsi4
        // Set the text Soal 8
        tvSoal8.text = quizResponse.data[7].question
        aSoal8.text = quizResponse.data[7].opsi1
        bSoal8.text = quizResponse.data[7].opsi2
        cSoal8.text = quizResponse.data[7].opsi3
        dSoal8.text = quizResponse.data[7].opsi4
        // Set the text Soal 9
        tvSoal9.text = quizResponse.data[8].question
        aSoal9.text = quizResponse.data[8].opsi1
        bSoal9.text = quizResponse.data[8].opsi2
        cSoal9.text = quizResponse.data[8].opsi3
        dSoal9.text = quizResponse.data[8].opsi4
        // Set the text Soal 10
        tvSoal10.text = quizResponse.data[9].question
        aSoal10.text = quizResponse.data[9].opsi1
        bSoal10.text = quizResponse.data[9].opsi2
        cSoal10.text = quizResponse.data[9].opsi3
        dSoal10.text = quizResponse.data[9].opsi4

    }

//    private fun getSelectedAnswer(a: RadioButton, b: RadioButton, c: RadioButton, d: RadioButton): String {
//        return when {
//            a.isChecked -> "A"
//            b.isChecked -> "B"
//            c.isChecked -> "C"
//            d.isChecked -> "D"
//            else -> ""
//        }
//    }

    companion object {
        const val EXTRA_TOKEN = "extra_token"
    }
}