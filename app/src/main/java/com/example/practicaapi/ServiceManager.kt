package com.example.practicaapi

import android.app.Activity
import android.content.Context

public object ServiceManager {
    fun getAuthenticationManager(context : Context, activity : Activity) : AuthenticationManager {
        return AuthenticationManager(context, activity)
    }
    fun getActivityManager(context : Context, activity : Activity) : ActivitiesManager {
        return ActivitiesManager(context, activity)
    }
}