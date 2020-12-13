package com.example.practicaapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.navigationView
import kotlinx.android.synthetic.main.activity_home.toolbar
import kotlinx.android.synthetic.main.navigation_drawer_layout.*
import kotlinx.android.synthetic.main.navigationview_header.view.*

class HomeActivity : AppCompatActivity(){
    private var navigationPosition : Int = 0

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
            this, drawerLayout, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.nav_app_bar_navigate_up_description
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
                    navigationPosition = R.id.navItemAllProducts
                    //navigateToFragment()
                }
            }
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawerLayout.closeDrawers()
            true
        }

        /*drawerLayout.addDrawerListener(object:DrawerLayout.DrawerListener{
            override fun onDrawerStateChanged(p0: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerSlide(p0: View, p1: Float) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerClosed(p0: View) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerOpened(p0: View) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })*/
        changeNavigationHeaderInfo()
    }

    private fun changeNavigationHeaderInfo() {
        val headerView = navigationView.getHeaderView(0)
        headerView.textEmail.text = "felejunier@hotmail.com"
    }

    private fun setUpDrawerLayout() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.nav_app_bar_navigate_up_description)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun navigateToFragment(fragmentToNavigate: Fragment) {
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

        if (navigationPosition == R.id.navItemAllProducts) {
            finish()
        } else {
            //Navigate to Inbox Fragment
            navigationPosition = R.id.navItemAllProducts
            navigateToFragment(AllProductsFragment.newInstance())
            navigationView.setCheckedItem(navigationPosition)
            toolbar.title = "Home"
        }
    }

/*    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Publication", Toast.LENGTH_SHORT).show()
            }
            R.id.action_refresh -> {
                Toast.makeText(this, "Android Store", Toast.LENGTH_SHORT).show()
            }
*//*            R.id.nav_news -> {
                Toast.makeText(this, "Newsletter", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_join -> {
                Toast.makeText(this, "Join Community", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_contact -> {
                Toast.makeText(this, "Contact us", Toast.LENGTH_SHORT).show()
            }*//*
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }*/

/*    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/

/*    */
}