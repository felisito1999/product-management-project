package com.example.practicaapi

import android.app.Activity
import android.content.Context

public object ServicerManager {
    fun getAuthenticationManager(context : Context, activity : Activity) : AuthenticationManager {
        return AuthenticationManager(context, activity)
    }
}