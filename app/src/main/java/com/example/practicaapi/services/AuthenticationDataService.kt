package com.example.practicaapi.services

import com.example.practicaapi.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationDataService {
    @POST("auth/local")
    fun getToken(@Body credentials : Credentials) : Call<TokenInfo>
    @POST("auth/local/register")
    fun signUpUser(@Body userInformation : RegisterModel) :Call<RegisterResponse>
}