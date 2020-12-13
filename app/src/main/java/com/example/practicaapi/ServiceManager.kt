package com.example.practicaapi

import android.app.Activity

public object ServiceManager {
    fun getProductManager(activity: Activity) : ProductManager{
        return ProductManager(activity)
    }
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