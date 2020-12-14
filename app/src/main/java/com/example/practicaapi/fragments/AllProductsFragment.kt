package com.example.practicaapi.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapi.models.ProductModel
import com.example.practicaapi.adapter.ProductRecyclerViewAdapter
import com.example.practicaapi.R
import com.example.practicaapi.activities.HomeActivity
import com.example.practicaapi.services.ServiceManager
import kotlinx.android.synthetic.main.activity_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AllProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllProductsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var adapter : ProductRecyclerViewAdapter? = null
    private var recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_allProducts)
    var products = mutableListOf<ProductModel>()

    private var productsStart = 0
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater?.inflate(R.layout.fragment_all_products, container, false)

        // Inflate the layout for this fragment
        getProductList(productsStart)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_allProducts)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        initScrollListener()


    }

    private fun initScrollListener() {
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int){
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if(!isLoading){
                    if(linearLayoutManager != null &&
                        linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                        products.size - 1){

                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    @Suppress("DEPRECATION")
    private fun loadMore() {
        products.add(ProductModel())
        adapter?.notifyItemInserted(products.size - 1)

        val handler = Handler()

        handler.postDelayed(Runnable {
//            products.removeAt(products.size - 1)

            val scrollPosition = products.size
            adapter?.notifyItemRemoved(scrollPosition)

            var currentSize = scrollPosition
            val nextLimit = currentSize + 10

            getProductList(productsStart)
            isLoading = false
        }, 2000)
    }

    private fun getProductList(start : Int) {
        //TODO: Add pagination to the recyclerView

            ServiceManager.getProductManager(requireActivity())
                .getProducts(productsStart){ productList ->

                if(products.size == 0){
                    products = productList as MutableList<ProductModel>
                    if (products != null) {
                        adapter = ProductRecyclerViewAdapter({productModel ->
                            Toast.makeText(context, "Hello my name is ${productModel.name}", Toast.LENGTH_SHORT).show()

                            val updateFragment = UpdateProductFragment.newInstance()
                            val bundle = Bundle()
                            bundle.putSerializable("product", productModel)
                            updateFragment.arguments = bundle

                            (activity as HomeActivity).navigateToFragment(updateFragment)


                        },requireContext())
                        adapter?.setProductsList(products)

                        recyclerView?.adapter = adapter

                        productsStart += 10
                    }
                }
                else {
                    products.removeAt(products.size - 1)
                    if(productList != null) {
                        var newProducts = productList
                        newProducts.forEach { newProduct ->
                            products.add(newProduct)
                        }
                        adapter?.notifyDataSetChanged()
                        productsStart += 10
                        recyclerView?.scrollToPosition(products.size- 9)
                    }

                }
            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): Fragment {
            return AllProductsFragment()
        }
//        @JvmStatic
/*        fun newInstance(param1: String?, param2: String?) =
            AllProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}