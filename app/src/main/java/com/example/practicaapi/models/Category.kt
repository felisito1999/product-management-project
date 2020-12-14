package com.example.practicaapi.models

import java.util.*

data class Category (
    var id : Int?,
    var name : String,
    var products : List<ProductModel>?,
    var published_at : String?
)