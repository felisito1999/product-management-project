package com.example.practicaapi

import android.content.Context

public class TokenManager(context: Context) {

    private val innerContext : Context = context

    fun storeAccessToken(token : String){
        val sharedPreferencesEdit = innerContext.getSharedPreferences(
            "DEFAULT_PREFERENCES",
            Context.MODE_PRIVATE
        ).edit()

        sharedPreferencesEdit.putString(innerContext.getString(R.string.access_token_key), token)
        sharedPreferencesEdit.commit()
    }

    private fun getAccessToken() : String?{
        return innerContext.getSharedPreferences("DEFAULT_PREFERENCES", Context.MODE_PRIVATE).getString(
            innerContext.getString(
                R.string.access_token_key
            ), ""
        )
    }
}