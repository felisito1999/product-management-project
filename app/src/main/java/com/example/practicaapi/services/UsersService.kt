package com.example.practicaapi.services

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.practicaapi.models.UserInfoModel
import retrofit2.Call
import retrofit2.Response

class UsersService(activity: Activity) {
    private var targetActivity = activity

    fun getUserInformation(onComplete: (UserInfoModel) -> Unit) {
        val token = ServiceManager.getTokenManager(targetActivity).getAccessToken()!!
        var service = RetrofitBuilderService.getAuthenticatedUsersInstance(targetActivity, token)

        service.getUserInformation().enqueue(object : retrofit2.Callback<UserInfoModel>{
            override fun onResponse(call: Call<UserInfoModel>, response: Response<UserInfoModel>) {
                var userResponse = response.body()!!
                onComplete.invoke(userResponse)
                Log.d("Info", "The user's information has been retrieved successfully")
            }

            override fun onFailure(call: Call<UserInfoModel>, t: Throwable) {
                Toast.makeText(targetActivity, "Could not get user's information", Toast.LENGTH_SHORT).show()
            }

        })

    }
}