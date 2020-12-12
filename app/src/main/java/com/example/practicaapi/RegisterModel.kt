package com.example.practicaapi

data class RegisterModel (
    val username : String,
    val email : String,
    val provider : String = "Email",
    val resetPasswordToken : String = "",
    val confirmationToken : String = "",
    val confirmed : Boolean = true,
    val blocked : Boolean = false,
    val role: String = "Authenticated",
    val created_by : String = "",
    val updated_by : String = ""
)
