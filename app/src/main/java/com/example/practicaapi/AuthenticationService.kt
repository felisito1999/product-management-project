package com.example.practicaapi

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/local")
    fun getToken(@Body credentials : Credentials)
    @POST("auth/local/register")
    fun signUpUser(@Body userInformation : RegisterModel)
}