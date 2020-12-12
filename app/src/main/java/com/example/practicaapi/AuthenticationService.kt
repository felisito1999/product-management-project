package com.example.practicaapi

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/local")
    fun getToken(@Body credentials : Credentials)
}