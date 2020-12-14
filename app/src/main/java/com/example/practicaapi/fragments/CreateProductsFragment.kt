package com.example.practicaapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.practicaapi.R
import com.example.practicaapi.models.Category
import com.example.practicaapi.models.ProductModel
import com.example.practicaapi.services.ServiceManager
import kotlinx.android.synthetic.main.fragment_create_products.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateProductsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateProductsFragment : Fragment(), AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var spinner = view?.findViewById<Spinner>(R.id.spinnerCategory)
    private lateinit var categorySpinnerAdapter : ArrayAdapter<String>

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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_create_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById<Spinner>(R.id.spinnerCategory)
        loadCategories()

        setListeners()
    }
    fun setListeners() {
        buttonSaveCreate.setOnClickListener {
            val sku = editTextSkuCreate.text.toString()
            val name = editTextNameCreate.text.toString()
            val type = editTextTypeCreate.text.toString()
            val upc = editTextUpcCreate.text.toString()
            val price = editTextPriceCreate.text.toString().toDouble()
            val shipping = editTextShippingCreate.text.toString().toDouble()
            val description = editTextDescriptionCreate.text.toString()
            val manufacturer = editTextManufacturerCreate.text.toString()
            val model = editTextModelCreate.text.toString()
            val url = ""
            val imageUrl = editTextImageUrlCreate.text.toString()

            val category = Category(null, spinner?.selectedItem.toString(), null, null)

            val categories = mutableListOf<Category>()
            categories.add(category)

            val product = ProductModel(null, sku, type, name,  upc, price, shipping, description, manufacturer, model, url, imageUrl, null, null, null, categories)

            ServiceManager.getProductManager(requireActivity()).saveProduct(product)
        }
    }

    fun loadCategories() {
        ServiceManager.getCategoryManager(requireActivity()).getCategories { categoryList ->

            if(categoryList != null){
                val categoryNamesCollection = mutableListOf<String>()
                categoryList.forEach { category ->
                    categoryNamesCollection.add(category.name)
                }
                categorySpinnerAdapter = ArrayAdapter<String>(requireActivity(), R.layout.support_simple_spinner_dropdown_item, categoryNamesCollection)
            }
            categorySpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner?.adapter = categorySpinnerAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateProductsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() : Fragment{
            return CreateProductsFragment()
        }
/*        fun newInstance(param1: String, param2: String) =
            CreateProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}