package com.example.practicaapi.services

import android.app.Activity
import com.example.practicaapi.services.ActivitiesManager
import com.example.practicaapi.services.AuthenticationManager
import com.example.practicaapi.services.ProductService
import com.example.practicaapi.services.TokenService

public object ServiceManager {
    fun getProductManager(activity: Activity) : ProductService {
        return ProductService(activity)
    }
    fun getAuthenticationManager(activity : Activity) : AuthenticationManager {
        return AuthenticationManager(activity)
    }
    fun getActivityManager(activity : Activity) : ActivitiesManager {
        return ActivitiesManager(activity)
    }
    fun getTokenManager(activity: Activity) : TokenService {
        return TokenService(activity)
    }
    fun getCategoryManager(activity: Activity) : CategoryService{
        return CategoryService(activity)
    }
}