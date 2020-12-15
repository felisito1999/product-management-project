package com.example.practicaapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.practicaapi.R
import com.example.practicaapi.models.RegisterModel
import com.example.practicaapi.services.ServiceManager
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        setupActivity()
    }

    private fun setupActivity() {
        btnSignUp.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
            val firstName = findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.editTextLastName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            val registerModel = RegisterModel(username = username, firstname = firstName,
                lastname = lastName, email = email, password = password)

            ServiceManager.getAuthenticationManager(this).singUp(registerModel)
        }

        textViewReturnLogin.setOnClickListener {
            ServiceManager.getActivityManager(this).goToLogin()
        }
    }
}