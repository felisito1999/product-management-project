package com.example.practicaapi

import java.util.*

data class Category (
    var id : Int,
    var name : String,
    var products : List<ProductModel>,
    var published_at : Date
)