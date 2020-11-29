package com.example.practicaapi

import android.app.Activity
import android.content.Context

public object ServiceManager {
    fun getAuthenticationManager(activity : Activity) : AuthenticationManager {
        return AuthenticationManager(activity)
    }
    fun getActivityManager(activity : Activity) : ActivitiesManager {
        return ActivitiesManager(activity)
    }
    fun getTokenManager(activity: Activity) : TokenManager {
        return TokenManager(activity)
    }
}