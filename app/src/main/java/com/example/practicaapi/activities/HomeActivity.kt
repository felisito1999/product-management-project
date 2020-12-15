package com.example.practicaapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.practicaapi.R
import com.example.practicaapi.fragments.AllProductsFragment
import com.example.practicaapi.fragments.CreateProductsFragment
import com.example.practicaapi.fragments.MainPageFragment
import com.example.practicaapi.fragments.UpdateProductFragment
import com.example.practicaapi.models.UserInfoModel
import com.example.practicaapi.services.ServiceManager
import kotlinx.android.synthetic.main.activity_home.navigationView
import kotlinx.android.synthetic.main.activity_home.toolbar
import kotlinx.android.synthetic.main.navigationview_header.view.*

class HomeActivity : AppCompatActivity(){
    private var navigationPosition : Int = 0
    lateinit var loggedUser : UserInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()

    }


    private fun initView() {
        setSupportActionBar(toolbar)
        setUpDrawerLayout()

        navigationPosition = R.id.navItemMainPage
        navigateToFragment(MainPageFragment.newInstance())
        navigationView.setCheckedItem(navigationPosition)
        toolbar.title = getString(R.string.home_string)


        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_navigate_up_description
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.navItemMainPage -> {
                    toolbar.title = getString(R.string.home_string)
                    navigationPosition= R.id.navItemMainPage
                    navigateToFragment(MainPageFragment.newInstance())
                }
                R.id.navItemAllProducts -> {
                    toolbar.title = getString(R.string.all_products_title)
                    navigationPosition = R.id.navItemAllProducts
                    navigateToFragment(AllProductsFragment.newInstance())
                }
                R.id.navItemCreateProduct -> {
                    toolbar.title = getString(R.string.create_product_title)
                    navigationPosition = R.id.navItemCreateProduct
                    navigateToFragment(CreateProductsFragment.newInstance())
                }
            }
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            true
        }
        loadUserInformation()
    }

    private fun changeNavigationHeaderInfo() {
        val headerView = navigationView.getHeaderView(0)
        headerView.textEmail.text = "Hello ${loggedUser.username}"
    }

    private fun loadUserInformation() {
        ServiceManager.getUserInformationManager(this).getUserInformation { user ->
            loggedUser = user

            changeNavigationHeaderInfo()
        }
    }

    private fun setUpDrawerLayout() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_navigate_up_description
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        if (navigationPosition == R.id.navItemMainPage) {
            finish()
        } else {
            navigationPosition = R.id.navItemMainPage
            navigateToFragment(MainPageFragment.newInstance())
            navigationView.setCheckedItem(navigationPosition)
            toolbar.title = "Main Page"
        }

    }
}