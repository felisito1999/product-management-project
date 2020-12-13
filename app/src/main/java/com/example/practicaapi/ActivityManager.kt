package com.example.practicaapi

import android.app.Activity
import android.content.Intent

class ActivitiesManager(activity: Activity) {

    private val targetActivity = activity

    fun goToLogin(){
        val loginActivityIntent = Intent(targetActivity, MainActivity::class.java)
        targetActivity.startActivity(loginActivityIntent)

        finishActivity()
    }

    fun goToHomeActivity(){
        val homeActivityIntent = Intent(targetActivity, HomeActivity::class.java)
        targetActivity.startActivity(homeActivityIntent)

        finishActivity()
    }

    fun goToSignUpActivity(){
        val signUpActivityIntent = Intent(targetActivity, SignUpActivity::class.java)
        targetActivity.startActivity(signUpActivityIntent)

        finishActivity()
    }

    fun finishActivity(){
        targetActivity.finish()
    }

    fun checkIfLoggedIn() {
        if (ServiceManager.getTokenManager(targetActivity).getAccessToken().isNullOrEmpty() && !targetActivity.javaClass.isInstance(MainActivity())) {
            goToLogin()
            finishActivity()
        }else if(targetActivity.javaClass.isInstance(MainActivity())){
            goToHomeActivity()
            finishActivity()
        }
    }

}