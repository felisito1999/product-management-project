package com.example.practicaapi

import android.app.Activity
import android.util.Log
import android.widget.Toast
import okhttp3.Callback
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response

class ProductManager(activity : Activity) {
    private var targetActivity = activity
    private var client = OkHttpClient()

    fun getProducts(onComplete: (List<Product>) -> Unit){
        val service = RetrofitBuilderHelper.getInstance(targetActivity)
        var products : List<Product> = emptyList<Product>()
    //TODO: Implement token authorization


        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        service.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                var productsResponse = response.body()
                onComplete.invoke(productsResponse!!)

                Log.d("Data", response.body()!![0].name)
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(targetActivity, "Could not load products", Toast.LENGTH_SHORT).show()
            }

        })
    }
}