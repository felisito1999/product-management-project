package com.example.practicaapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private var adapter : ProductRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        ServiceManager.getActivityManager(this).checkIfLoggedIn()


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_allProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductRecyclerViewAdapter(this)

        recyclerView.adapter = adapter

        getProductList()
    }

    private fun getProductList() {
        var products = emptyList<Product>()
        ServiceManager.getProductManager(this).getProducts{ productList ->
            products = productList
            if (products != null) {
                adapter?.setProductsList(products)
            }
        }
    }
}