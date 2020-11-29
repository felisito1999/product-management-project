package com.example.practicaapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class ActivitiesManager(context :Context, activity: Activity) {

    private val innerContext = context
    private val innerActivity = activity

    public fun goToHomeActivity(){
        val homeActivityIntent = Intent(innerActivity, HomeActivity::class.java)
        innerContext.startActivity(homeActivityIntent)

        innerActivity.finish()
    }
}