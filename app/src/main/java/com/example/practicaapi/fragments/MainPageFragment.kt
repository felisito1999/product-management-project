package com.example.practicaapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.practicaapi.R
import com.example.practicaapi.services.ServiceManager
import kotlinx.android.synthetic.main.fragment_main_page.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/*private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"*/

/**
 * A simple [Fragment] subclass.
 * Use the [MainPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var productCount : Int = 0
    private lateinit var username : String

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
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserInformation()
        loadCountProducts()
        setListeners()
    }

    private fun setListeners() {
        buttonSignOut.setOnClickListener {
            Toast.makeText(activity, "Sorry to see you go, come again soon", Toast.LENGTH_LONG)
            ServiceManager.getTokenManager(requireActivity()).deleteToken()
            ServiceManager.getActivityManager(requireActivity()).goToLogin()
        }
    }

    private fun loadCountProducts() {
        ServiceManager.getProductManager(requireActivity()).getProductsCount {productCountResponse ->
            productCount = productCountResponse
            changeProductCount(productCount)
        }


    }

    private fun getUserInformation() {
        ServiceManager.getUserInformationManager(requireActivity()).getUserInformation { userInfo ->
            username = userInfo.username

            changeWelcomeSign(username)
        }
    }

    private fun changeProductCount(productCount : Int){
        textViewProductsCount.text = productCount.toString()
    }

    private fun changeWelcomeSign(username : String) {
        textViewWelcomeSign.text = "Welcome, ${username}"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance() : Fragment{
            return MainPageFragment()
        }
/*        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}