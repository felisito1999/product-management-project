package com.example.practicaapi

import android.app.Activity
import android.content.Context
import android.widget.Toast
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class AuthenticationManager(context : Context, loginActivity : Activity) {
    private lateinit var tokenManager : TokenManager
    private var innerContext = context
    private var loginActivity = loginActivity
    private var client = OkHttpClient()

    fun logIn(email : String, password : String) {
         if (checkEmptyFields(email, password)){
             if(validateEmail(email) && validatePassword(password)){
                 val retrofitManager = Retrofit.Builder()
                     .baseUrl(innerContext.getString(R.string.api_base_url))
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(client)
                     .build()

                 var service = retrofitManager.create(ProductsService::class.java)

                 var credentials = Credentials(email, password)
/*        credentials.identifier = "console@strapi.io"
        credentials.password = "123456"*/

                 val getTokenRequest = service.getToken(credentials)
                 getTokenRequest.enqueue( object : retrofit2.Callback<TokenInfo>{
                     override fun onResponse(
                         call: retrofit2.Call<TokenInfo>,
                         response: retrofit2.Response<TokenInfo>
                     ) {
                         if (response.isSuccessful){
                             loginActivity.runOnUiThread {
                                 run{
                                     Toast.makeText(innerContext, response.body()?.jwt, Toast.LENGTH_SHORT).show()
                                 }
                             }
                         }
                     }

                     override fun onFailure(call: retrofit2.Call<TokenInfo>, t: Throwable) {
                         loginActivity.runOnUiThread {
                             run{
                                 Toast.makeText(innerContext, "ERROR", Toast.LENGTH_SHORT).show()
                             }
                         }
                     }

                 })
             }
         }
    }
    fun checkEmptyFields(email: String, password: String) : Boolean {
        return !email.isNullOrBlank() && !password.isNullOrBlank()
    }

    fun validateEmail(email: String) : Boolean {

        var atCount :Int = 0

        val minimumLength = 10
        val emailLength = email.length

        email.forEach { Char ->
            if (Char === '@'){
                atCount += 1
            }
        }

        return minimumLength <= emailLength && atCount == 1
    }

    fun validatePassword(password: String) : Boolean {
        val minimumLength = 6

        return minimumLength <= password.length
    }
}