package com.example.practicaapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("current_access_token", getAccessToken()!!)

/*        if (getAccessToken() != "") {
            goToHomeActivity()
        }*/
        val auth = AuthManager()

        btn_login.setOnClickListener {
            val email = txt_emailAddress.text.toString()
            val password = txt_password.text.toString()

            if (auth.validateEmail(email) && auth.validatePassword(password)
                && auth.checkEmptyFields(email, password)){
                //loadNewAccessToken(email, password)
            }
            else {
                nonSuccessfulLoginAlert()
            }
        }
//        loadNewAccessToken()

        val retrofitManager = Retrofit.Builder()
            .baseUrl(getString(R.string.api_base_url))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        var service = retrofitManager.create(ProductsService::class.java)

        var credentials = Credentials("console@strapi.io", "123456")
/*        credentials.identifier = "console@strapi.io"
        credentials.password = "123456"*/

        val getTokenRequest = service.getToken(credentials)
        getTokenRequest.enqueue( object : retrofit2.Callback<TokenInfo>{
            override fun onResponse(
                call: retrofit2.Call<TokenInfo>,
                response: retrofit2.Response<TokenInfo>
            ) {
               if (response.isSuccessful){
                   runOnUiThread {
                       run{
                           Toast.makeText(this@MainActivity, response.body()?.jwt, Toast.LENGTH_SHORT).show()
                       }
                   }
               }
            }

            override fun onFailure(call: retrofit2.Call<TokenInfo>, t: Throwable) {
                runOnUiThread {
                    run{
                        Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    private fun loadNewAccessToken(email: String, password: String) {

        val apiBaseUrl = getString(R.string.api_base_url)

        val mediaType = "application/json".toMediaType()

/*        val requestBody = ("{ " +
                "\"identifier\" : \@"console@strapi.io\", " +
        "\"password\": \"123456\" " +
                "}").toRequestBody(mediaType)*/

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
    }

    fun storeAccessToken(token: String?){
        val sharedPreferencesEdit = getSharedPreferences(
            "DEFAULT_PREFERENCES",
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferencesEdit.putString(getString(R.string.access_token_key), token)
        sharedPreferencesEdit.commit()
    }

    private fun getAccessToken() : String?{
        return getSharedPreferences("DEFAULT_PREFERENCES", Context.MODE_PRIVATE).getString(
            getString(
                R.string.access_token_key
            ), ""
        )
    }

    private fun goToHomeActivity(){
        val homeActivityIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeActivityIntent)

        this.finish()
    }

    private fun nonSuccessfulLoginAlert() {
        val toast = Toast.makeText(
            this,
            "The user login data is not correct, check your credentials and try again",
            Toast.LENGTH_LONG
        )
        toast.show()
    }
}

public class AuthResponse() {
    lateinit var jwt : String
}

