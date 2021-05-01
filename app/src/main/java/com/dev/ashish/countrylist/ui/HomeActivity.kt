package com.dev.ashish.countrylist.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.dev.ashish.countrylist.R
import com.dev.ashish.countrylist.fragment.CountryFragment
import kotlinx.android.synthetic.main.activity_main.*

//
// Created by Ashish on 01/05/21.
//

class HomeActivity : BaseActivity() {

    private val TAG = HomeActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if(savedInstanceState == null) {
            showFragment(CountryFragment.getInstance())
        }
    }


    /**
     * exit menu click event
     */
    private fun menuExitClicked() {
        finish()
    }


    /**
     * Menu Options Setup
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    /**
     * Menu Item Click Event
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exit -> menuExitClicked()
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    /**
     * Load Fragment
     */
    fun showFragment(newFragment: Fragment) {
        val tag = newFragment::class.java.simpleName
        supportFragmentManager.beginTransaction().replace(R.id.container, newFragment, tag)
            .addToBackStack(tag).commitAllowingStateLoss()
    }



    override fun onBackPressed() {
       if(supportFragmentManager.backStackEntryCount <=1){
           finish()
       }else{
           supportFragmentManager.popBackStack()
       }
    }


}