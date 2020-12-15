package com.example.practicaapi.services

import com.example.practicaapi.models.UserInfoModel
import retrofit2.Call
import retrofit2.http.GET

interface UsersDataService {
    @GET("users/me")
    fun getUserInformation() : Call<UserInfoModel>
}