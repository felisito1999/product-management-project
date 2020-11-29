package com.example.practicaapi

import android.app.Activity
import android.app.Service
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
                                     ServiceManager.getActivityManager(innerContext, loginActivity).goToHomeActivity()
                                 }
                             }
                         }
                         else {
                             nonSuccessfulLoginAlert()
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
             else {
                 nonValidDataAlert()
             }
         }
        else{
             emptyLoginFieldsAlert()
         }
    }
    private fun checkEmptyFields(email: String?, password: String?) : Boolean {
        return !email.isNullOrBlank() && !password.isNullOrBlank()
    }

    private fun validateEmail(email: String) : Boolean {

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

    private fun validatePassword(password: String) : Boolean {
        val minimumLength = 6

        return minimumLength <= password.length
    }


    private fun nonSuccessfulLoginAlert() {
        val toast = Toast.makeText(
            innerContext,
            "The user login data is not correct, check your credentials and try again",
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    private fun emptyLoginFieldsAlert() {
        val toast = Toast.makeText(innerContext, "There are empty fields, fill them and try again", Toast.LENGTH_LONG)
        toast.show()
    }

    private fun nonValidDataAlert() {
        val toast = Toast.makeText(innerContext, "The data is not correct, try again", Toast.LENGTH_SHORT)
        toast.show()
    }
}