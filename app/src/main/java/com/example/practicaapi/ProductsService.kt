package com.example.practicaapi

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ProductsService {

    @POST("auth/local")
    fun getToken(@Body credentials : Credentials) : Call<TokenInfo>
    @GET("products")
    fun getProducts(@Header("Authorization") token : String, @Query("_limit") limit: Int = 10) : Call<List<Product>>

}


