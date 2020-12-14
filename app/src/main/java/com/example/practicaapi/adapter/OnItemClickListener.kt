package com.example.practicaapi.adapter

import com.example.practicaapi.models.ProductModel

interface OnItemClickListener {
    fun onItemClick(productModel : ProductModel)
}