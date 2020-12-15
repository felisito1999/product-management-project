package com.example.practicaapi.services

import android.app.Activity
import android.content.Intent
import com.example.practicaapi.activities.HomeActivity
import com.example.practicaapi.activities.MainActivity
import com.example.practicaapi.activities.SignUpActivity

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
        if (ServiceManager.getTokenManager(targetActivity).getAccessToken().isNullOrEmpty() && !targetActivity.javaClass.isInstance(
                MainActivity()
            )) {
            goToLogin()
            finishActivity()
        }else if(!targetActivity.javaClass.isInstance(MainActivity())){
            goToHomeActivity()
            finishActivity()
        }
    }

}