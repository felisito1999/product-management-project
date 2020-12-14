package com.example.practicaapi.services

import com.example.practicaapi.models.Credentials
import com.example.practicaapi.models.ProductCountResponse
import com.example.practicaapi.models.ProductModel
import com.example.practicaapi.models.TokenInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ProductsDataService {

    @GET("products")
    fun getProducts(@Query("_start") start: Int, @Query("_limit") limit: Int = 10, @Query("_sort") sort: String = "id") : Call<List<ProductModel>>
    @GET("products/count")
    fun getProductsCount() : Call<ProductCountResponse>
    @POST("products")
    fun saveProduct(@Body product: ProductModel) : Call<ProductModel>
    @PUT("products/{id}")
    fun updateProduct(@Body product : ProductModel, @Path("id") id : Int) : Call<ProductModel>
    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id : Int) : Call<ResponseBody>
}


