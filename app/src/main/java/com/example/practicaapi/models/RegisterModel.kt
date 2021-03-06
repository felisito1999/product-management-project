package com.example.practicaapi.models

data class RegisterModel (
    var username : String,
    var firstname : String,
    var lastname : String,
    var email : String,
    val provider : String = "Email",
    var password : String,
    val resetPasswordToken : String = "",
    val confirmationToken : String = "",
    val confirmed : Boolean = true,
    val blocked : Boolean = false,
    val role: String = "Authenticated",
    val created_by : String = "",
    val updated_by : String = ""
)
