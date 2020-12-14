package com.example.practicaapi.services

import android.content.Context
import com.example.practicaapi.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilderService{
    companion object{
        fun getProductInstance(context : Context) : ProductsDataService {
            val client = OkHttpClient()
            val retrofitManager = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitManager.create(ProductsDataService::class.java)
        }

        fun getAuthenticatedProductInstance(context: Context, token: String) : ProductsDataService {
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

            return retrofitManager.create(ProductsDataService::class.java)
        }

        fun getAuthenticatedCategoryInstance(context: Context, token: String) : CategoriesDataService {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
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

            return retrofitManager.create(CategoriesDataService::class.java)
        }

        fun getAuthenticationInstance(context: Context) : AuthenticationDataService {
            val client = OkHttpClient()

            val retrofitManager = Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitManager.create(AuthenticationDataService::class.java)
        }
    }
}