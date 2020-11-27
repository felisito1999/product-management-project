package com.example.practicaapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ProductsService {

    @POST("auth/local")
    fun getToken(@Body credentials : Credentials) : Call<TokenInfo>
    fun getProducts() : List<Products>

}

