package com.example.practicaapi

import android.app.Activity
import android.util.Log
import android.widget.Toast
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response

class ProductManager(activity : Activity) {
    private var targetActivity = activity
    private var client = OkHttpClient()

    fun getProducts(start: Int, onComplete: (List<ProductModel>) -> Unit){
        val service = RetrofitBuilderHelper.getInstance(targetActivity)
        var products : List<ProductModel> = emptyList<ProductModel>()
    //TODO: Implement token authorization

        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!

        service.getProducts(start).enqueue(object : retrofit2.Callback<List<ProductModel>> {
            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                var productsResponse = response.body()
                onComplete.invoke(productsResponse!!)

                Log.d("Data", response.body()!![0].name)
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Toast.makeText(targetActivity, "Could not load products", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getProductsCount(onComplete: (ProductCountResponse) -> Unit){
        val service = RetrofitBuilderHelper.getInstance(targetActivity)

        service.getProductsCount().enqueue(object : retrofit2.Callback<ProductCountResponse>{
            override fun onResponse(
                call: Call<ProductCountResponse>,
                response: Response<ProductCountResponse>
            ) {
                var productCountResponse = response.body()
                onComplete.invoke(productCountResponse!!)

                Log.d("Product count", response.body()!!.count.toString())
            }

            override fun onFailure(call: Call<ProductCountResponse>, t: Throwable) {
                Toast.makeText(
                    targetActivity,
                    "Could not get the quantity of products in the system",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}