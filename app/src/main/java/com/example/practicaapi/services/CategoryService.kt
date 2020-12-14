package com.example.practicaapi.services

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.practicaapi.models.Category
import com.example.practicaapi.models.ProductModel
import retrofit2.Call
import retrofit2.Response

class CategoryService(activity : Activity) {
    private var targetActivity = activity

    fun getCategories(onComplete : (List<Category>) -> Unit){
            val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
            val service = RetrofitBuilderService.getAuthenticatedCategoryInstance(targetActivity, token)

            service.getCategories().enqueue(object : retrofit2.Callback<List<Category>> {
                override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                    var categoriesResponse = response.body()
                    onComplete.invoke(categoriesResponse!!)

                    Log.d("Data", response.body()!![0].name)
                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    Toast.makeText(targetActivity, "Could not load products", Toast.LENGTH_SHORT).show()
                }
            })
    }

}