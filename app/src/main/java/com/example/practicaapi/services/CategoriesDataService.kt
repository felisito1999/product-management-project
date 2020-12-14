package com.example.practicaapi.services

import com.example.practicaapi.models.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesDataService {
    @GET("categories")
    fun getCategories(@Query("_limit") limit : Int = 5) : Call<List<Category>>
}