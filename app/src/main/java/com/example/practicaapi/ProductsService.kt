package com.example.practicaapi

import retrofit2.Call
import retrofit2.http.*


interface ProductsService {

    @POST("auth/local")
    fun getToken(@Body credentials : Credentials) : Call<TokenInfo>
    @GET("products")
    fun getProducts(@Query("_start") start: Int, @Query("_limit") limit: Int = 10, @Query("_sort") sort: String = "id") : Call<List<ProductModel>>
    @GET("products/count")
    fun getProductsCount() : Call<ProductCountResponse>
    //@Header("Authorization") token : String,

}


