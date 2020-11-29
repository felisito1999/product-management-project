package com.example.practicaapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

/*    private val client = OkHttpClient()
    private val authManager = AuthenticationManager(this, MainActivity())
    private val services = ServicerManager.getAuthenticationManager(this, MainActivity())*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActivity()
    }

    private fun nonSuccessfulLoginAlert() {
        val toast = Toast.makeText(
            this,
            "The user login data is not correct, check your credentials and try again",
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    private fun setupActivity(){
        btn_login.setOnClickListener {

            val email = txt_emailAddress.text.toString()
            val password = txt_password.text.toString()

            ServicerManager.getAuthenticationManager(this, MainActivity()).logIn(email, password)
        }
    }
}

/*private fun loadNewAccessToken(email: String, password: String) {

    val apiBaseUrl = getString(R.string.api_base_url)

    val mediaType = "application/json".toMediaType()

    val requestBody = ("{ " +
            "\"identifier\" : \"${email}\", " +
            "\"password\": \"${password}\" " +
            "}").toRequestBody(mediaType)

    val request = Request.Builder()
        .url(apiBaseUrl + "auth/local")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.i("okhttpresponse", e.toString())
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
//                    hello.text = it.message
                if (!response.isSuccessful) {
                    runOnUiThread {
                        nonSuccessfulLoginAlert()
                    }
                    throw IOException("Unexpected code $response")
                }

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                val moshi = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                val jsonAdapter: JsonAdapter<AuthResponse> =
                    moshi.adapter(AuthResponse::class.java)

                val authResponse: AuthResponse? =
                    jsonAdapter.fromJson(response.body?.string())!!

                runOnUiThread {
                    run() {
                        storeAccessToken(authResponse?.jwt)

                        goToHomeActivity()
                    }
                }

                if (authResponse != null) {
                    Log.i("okhttpresponse", authResponse.jwt)
                }
            }
        }
    })
}*/
