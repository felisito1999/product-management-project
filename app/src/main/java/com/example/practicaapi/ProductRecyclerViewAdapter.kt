package com.example.practicaapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_item_recycler_view.view.*

class ProductRecyclerViewAdapter internal constructor(
    context : Context) : RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var products = emptyList<ProductModel>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.product_item_recycler_view, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = products[position]

        holder.productItemView.textview_sku_value.text = product.sku
        holder.productItemView.textview_model_value.text = product.model
        holder.productItemView.textview_name_value.text = product.name
        holder.productItemView.textview_type_value.text = product.type
        holder.productItemView.textview_price_value.text = product.price.toString()
        DownloadImageFromInternet(holder.productItemView.imageview_product).execute(product.image)
    }

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val productItemView : LinearLayout = itemView.findViewById(R.id.product_item_recycler_view)
    }
    internal fun setProductsList(productsCollection : List<ProductModel>){
        this.products = productsCollection
        notifyDataSetChanged()
    }
}