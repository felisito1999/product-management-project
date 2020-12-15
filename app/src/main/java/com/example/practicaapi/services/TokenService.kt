package com.example.practicaapi.services

import android.content.Context
import com.example.practicaapi.R

public class TokenService(context: Context) {

    private val innerContext : Context = context

    fun storeAccessToken(token : String){
        val sharedPreferencesEdit = innerContext.getSharedPreferences(
            "DEFAULT_PREFERENCES",
            Context.MODE_PRIVATE
        ).edit()

        sharedPreferencesEdit.putString(innerContext.getString(R.string.access_token_key), token)
        sharedPreferencesEdit.commit()
    }

    fun getAccessToken() : String?{
        return innerContext.getSharedPreferences("DEFAULT_PREFERENCES", Context.MODE_PRIVATE).getString(
            innerContext.getString(
                R.string.access_token_key
            ), ""
        )
    }

    fun deleteToken() {
        val sharedPreferencesEdit = innerContext.getSharedPreferences("DEFAULT_PREFERENCES", Context.MODE_PRIVATE).edit()
        sharedPreferencesEdit.clear()
        sharedPreferencesEdit.commit()
    }
}