package com.example.practicaapi.models

import com.example.practicaapi.models.User

class RegisterResponse{
    lateinit var jwt: String
    lateinit var user : User
}