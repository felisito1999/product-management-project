package com.example.practicaapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practicaapi.R
import com.example.practicaapi.activities.HomeActivity
import com.example.practicaapi.models.ProductModel
import com.example.practicaapi.services.ServiceManager
import kotlinx.android.synthetic.main.fragment_update_product.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
/*    private var param1: String? = null
    private var param2: String? = null*/

    private lateinit var product : ProductModel

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
        return inflater.inflate(R.layout.fragment_update_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle? = this.arguments
        product = bundle?.getSerializable("product") as ProductModel

        editTextUpdateSku.setText(product.sku)
        editTextUpdateName.setText(product.name)
        editTextUpdateType.setText(product.type)
        editTextUpdateUpc.setText(product.upc)
        editTextUpdatePrice.setText(product.price.toString())
        editTextUpdateShipping.setText(product.shipping.toString())
        editTextUpdateDescription.setText(product.description)
        editTextUpdateManufacturer.setText(product.manufacturer)
        editTextUpdateModel.setText(product.model)
        editTextUpdateImageUrl.setText(product.image)

        setListeners()
    }

    fun setListeners() {
        buttonUpdate.setOnClickListener {
            product.sku = editTextUpdateSku.text.toString()
            product.name = editTextUpdateName.text.toString()
            product.type = editTextUpdateType.text.toString()
            product.upc = editTextUpdateUpc.text.toString()
            product.price = editTextUpdatePrice.text.toString().toDouble()
            product.shipping = editTextUpdateShipping.text.toString().toDouble()
            product.description = editTextUpdateDescription.text.toString()
            product.manufacturer = editTextUpdateManufacturer.text.toString()
            product.model = editTextUpdateModel.text.toString()
            product.image = editTextUpdateImageUrl.text.toString()

            ServiceManager.getProductManager(requireActivity()).updateProduct(product)

            val allProductsFragment = AllProductsFragment.newInstance()
            (activity as HomeActivity).navigateToFragment(allProductsFragment)
        }
        buttonDelete.setOnClickListener {
            val id : Int? = product.id

            ServiceManager.getProductManager(requireActivity()).deleteProduct(id!!)
            val allProductsFragment = AllProductsFragment.newInstance()
            (activity as HomeActivity).navigateToFragment(allProductsFragment)
        }
        buttonCancelUpdate.setOnClickListener {
            val allProductsFragment = AllProductsFragment.newInstance()
            (activity as HomeActivity).navigateToFragment(allProductsFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpdateProductFragment.
         */
        // TODO: Rename and change types and number of parameters

        fun newInstance() : Fragment{
            return UpdateProductFragment()
        }
/*        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}