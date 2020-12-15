package com.example.practicaapi.services

import android.app.Activity
import android.widget.Toast
import com.example.practicaapi.models.Credentials
import com.example.practicaapi.models.RegisterModel
import com.example.practicaapi.models.RegisterResponse
import com.example.practicaapi.models.TokenInfo
import retrofit2.Call
import retrofit2.Response

class AuthenticationManager(activity : Activity) {

    private var targetActivity = activity

    fun singUp(registerModel: RegisterModel){
        val username = registerModel.username
        val firstName = registerModel.firstname
        val lastname = registerModel.lastname
        val email = registerModel.email
        val password = registerModel.password
        if(checkEmptyFields(email, password)){
            if(validateEmail(email) && validatePassword(password)){
                val service = RetrofitBuilderService.getAuthenticationInstance(targetActivity)

                val userInformation = RegisterModel(username = username, firstName, lastname, email = email, password = password )
                val signUpUserRequest = service.signUpUser(userInformation)

                signUpUserRequest.enqueue(object : retrofit2.Callback<RegisterResponse>{
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                       if(response.isSuccessful && response.body() != null){
                           targetActivity.runOnUiThread {
                               run{
                                   Toast.makeText(targetActivity, "Welcome ${response.body()!!.user.username}", Toast.LENGTH_LONG).show()
                                   ServiceManager.getTokenManager(targetActivity).storeAccessToken(response.body()!!.jwt)
                                   ServiceManager.getActivityManager(targetActivity).goToHomeActivity()
                               }
                           }
                       }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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
        else {
            emptyLoginFieldsAlert()
        }
    }

    fun logIn(email : String, password : String) {
         if (checkEmptyFields(email, password)){
             if(validateEmail(email) && validatePassword(password)){
/*                  val retrofitManager = Retrofit.Builder()
                     .baseUrl(targetActivity.getString(R.string.api_base_url))
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(client)
                     .build()
*/
                 val service = RetrofitBuilderService.getAuthenticationInstance(targetActivity)

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