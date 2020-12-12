package com.example.practicaapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        getProductList()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView_allProducts)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getProductList() {
        var products = emptyList<Product>()
            ServiceManager.getProductManager(requireActivity()).getProducts{ productList ->
                products = productList
                if (products != null) {
                    adapter = ProductRecyclerViewAdapter(requireActivity())
                    adapter?.setProductsList(products)

                    recyclerView?.adapter = adapter
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