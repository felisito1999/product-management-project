package com.example.practicaapi.services

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.practicaapi.models.ProductCountResponse
import com.example.practicaapi.models.ProductModel
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ProductService(activity : Activity) {
    private var targetActivity = activity
    private var client = OkHttpClient()

    fun getProducts(start: Int, onComplete: (List<ProductModel>) -> Unit){
        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        val service = RetrofitBuilderService.getAuthenticatedProductInstance(targetActivity, token)

        var products : List<ProductModel> = emptyList<ProductModel>()
    //TODO: Implement token authorization

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
        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        val service = RetrofitBuilderService.getAuthenticatedProductInstance(targetActivity, token)

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

    fun saveProduct(product : ProductModel){
        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        val service = RetrofitBuilderService.getAuthenticatedProductInstance(targetActivity, token)

        service.saveProduct(product).enqueue(object: retrofit2.Callback<ProductModel>{
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                Toast.makeText(targetActivity, "Product added successfully", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                Toast.makeText(
                    targetActivity,
                    "Could not save the product",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    fun updateProduct(product : ProductModel){
        val productId = product.id!!
        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        val service = RetrofitBuilderService.getAuthenticatedProductInstance(targetActivity, token)

        service.updateProduct(product, productId).enqueue(object: retrofit2.Callback<ProductModel>{
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                val updatedProduct = response.body()
                Toast.makeText(targetActivity,
                    "The product ${updatedProduct?.name} has been updated",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                Toast.makeText(targetActivity,
                    "The product could not be updated",
                    Toast.LENGTH_SHORT).show()

            }

        })
    }

    fun deleteProduct(id : Int){
        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        val service = RetrofitBuilderService.getAuthenticatedProductInstance(targetActivity, token)

        service.deleteProduct(id).enqueue(object: retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(targetActivity,
                    "The product has been deleted successfully",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(targetActivity,
                    "The product could not be deleted",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}