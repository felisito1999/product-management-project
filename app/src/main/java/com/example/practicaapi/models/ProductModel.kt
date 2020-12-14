package com.example.practicaapi.models

import com.example.practicaapi.models.Category

data class ProductModel (
    var id : Int?,
    var sku : String?,
    var type : String,
    var name : String,
    var upc : String,
    var price : Double,
    var shipping : Double,
    var description : String,
    var manufacturer : String,
    var model : String,
    var url : String?,
    var image : String?,
    var published_at : String?,
    var created_at: String?,
    var updated_at : String?,
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
