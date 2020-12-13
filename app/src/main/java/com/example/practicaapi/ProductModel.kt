package com.example.practicaapi

import java.util.*

data class ProductModel (
    var id : Int,
    var sku : String,
    var name : String,
    var type : String,
    var upc : String,
    var price : Double,
    var shipping : Double,
    var description : String,
    var manufacturer : String,
    var model : String,
    var url : String,
    var image : String,
    var published_at : String,
    var created_at: String,
    var updated_at : String,
    var categories : List<Category>
){
    constructor() : this(
        -1, "", "",
        "","", 0.00,
        0.00, "", "",
        "", "", "",
        "", "", "",
        emptyList()
    )
}
