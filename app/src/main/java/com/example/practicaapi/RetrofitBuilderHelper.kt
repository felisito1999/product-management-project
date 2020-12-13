package com.example.practicaapi

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderHelper{
    companion object{
        fun getProductInstance(context : Context) : ProductsService{
            val client = OkHttpClient()
            val retrofitManager = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitManager.create(ProductsService::class.java)
        }

        fun getAuthenticatedInstance(context: Context, token: String) : ProductsService {
            val client = OkHttpClient.Builder()
                .addInterceptor{ chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()

                    chain.proceed(newRequest)
                }
                .build()

            val retrofitManager = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitManager.create(ProductsService::class.java)
        }

        fun getAuthenticationInstance(context: Context) : AuthenticationService{
            val client = OkHttpClient()

            val retrofitManager = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitManager.create(AuthenticationService::class.java)
        }
    }
}