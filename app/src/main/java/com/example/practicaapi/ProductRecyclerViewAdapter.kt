package com.example.practicaapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_item_recycler_view.view.*

class ProductRecyclerViewAdapter internal constructor(
    context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    private var products = mutableListOf<ProductModel>()

    private val viewItemType = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == viewItemType){
            val itemView = inflater.inflate(R.layout.product_item_recycler_view, viewGroup, false)
            MyViewHolder(itemView)
        } else {
            val itemView = inflater.inflate(R.layout.item_loading, viewGroup, false)
            LoadingViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is MyViewHolder){
            val product = products[position]

            holder.productItemView.textview_sku_value.text = product.sku
            holder.productItemView.textview_model_value.text = product.model
            holder.productItemView.textview_name_value.text = product.name
            holder.productItemView.textview_type_value.text = product.type
            holder.productItemView.textview_price_value.text = product.price.toString()
            DownloadImageFromInternet(holder.productItemView.imageview_product).execute(product.image)
        }
        else if(holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val productItemView : LinearLayout = itemView.findViewById(R.id.product_item_recycler_view)
    }
    inner class LoadingViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
    internal fun setProductsList(productsCollection : MutableList<ProductModel>){
        this.products = productsCollection
        notifyDataSetChanged()
    }
    private fun showLoadingView(viewHolder : LoadingViewHolder, position: Int) {

    }
}