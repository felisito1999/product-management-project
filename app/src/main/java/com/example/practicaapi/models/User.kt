package com.example.practicaapi.models

import com.example.practicaapi.models.Role

data class User (
    var id : Int,
    var username : String,
    var email : String,
    var provider : String,
    var confirmed : Boolean,
    var blocked : Boolean,
    var role : Role,
    var created_at : String,
    var updated_at : String
)
