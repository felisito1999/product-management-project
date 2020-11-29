package com.example.practicaapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        ServiceManager.getActivityManager(this).checkIfLoggedIn()
        //agregar métodos
    }
}