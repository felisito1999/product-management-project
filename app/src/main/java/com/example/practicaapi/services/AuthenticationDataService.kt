package com.example.practicaapi.services

import com.example.practicaapi.models.Credentials
import com.example.practicaapi.models.RegisterModel
import com.example.practicaapi.models.RegisterResponse
import com.example.practicaapi.models.TokenInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationDataService {
    @POST("auth/local")
    fun getToken(@Body credentials : Credentials) : Call<TokenInfo>
    @POST("auth/local/register")
    fun signUpUser(@Body userInformation : RegisterModel) :Call<RegisterResponse>
}