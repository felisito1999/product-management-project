package com.example.practicaapi

import android.app.Activity
import android.content.Context
import android.widget.Toast
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthenticationManager(loginActivity : Activity) {

    private var targetActivity = loginActivity
    private var client = OkHttpClient()

    fun logIn(email : String, password : String) {
         if (checkEmptyFields(email, password)){
             if(validateEmail(email) && validatePassword(password)){
/*                  val retrofitManager = Retrofit.Builder()
                     .baseUrl(targetActivity.getString(R.string.api_base_url))
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(client)
                     .build()
*/
                 val service = RetrofitBuilderHelper.getInstance(targetActivity)

                 val credentials = Credentials(email, password)

                 val getTokenRequest = service.getToken(credentials)
                 getTokenRequest.enqueue( object : retrofit2.Callback<TokenInfo>{
                     override fun onResponse(
                         call: retrofit2.Call<TokenInfo>,
                         response: retrofit2.Response<TokenInfo>
                     ) {
                         if (response.isSuccessful && response.body() != null){
                             targetActivity.runOnUiThread {
                                 run{
                                     val jwt = response.body()!!.jwt
                                     Toast.makeText(targetActivity, "Successful login", Toast.LENGTH_SHORT).show()
                                     ServiceManager.getTokenManager(targetActivity).storeAccessToken(jwt)
                                     ServiceManager.getActivityManager(targetActivity).goToHomeActivity()
                                 }
                             }
                         }
                         else {
                             nonSuccessfulLoginAlert()
                         }
                     }
                     override fun onFailure(call: retrofit2.Call<TokenInfo>, t: Throwable) {
                         targetActivity.runOnUiThread {
                             run{
                                 Toast.makeText(targetActivity, "ERROR", Toast.LENGTH_SHORT).show()
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

        var atCount  = 0

        val minimumLength = 10
        val emailLength = email.length

        email.forEach { Char ->
            if (Char == '@'){
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
            targetActivity,
            "The user login data is not correct, check your credentials and try again",
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    private fun emptyLoginFieldsAlert() {
        val toast = Toast.makeText(targetActivity, "There are empty fields, fill them and try again", Toast.LENGTH_LONG)
        toast.show()
    }

    private fun nonValidDataAlert() {
        val toast = Toast.makeText(targetActivity, "The data is not correct, try again", Toast.LENGTH_SHORT)
        toast.show()
    }
}