package com.example.practicaapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/local")
    fun getToken(@Body credentials : Credentials) : Call<TokenInfo>
    @POST("auth/local/register")
    fun signUpUser(@Body userInformation : RegisterModel) :Call<RegisterResponse>
}