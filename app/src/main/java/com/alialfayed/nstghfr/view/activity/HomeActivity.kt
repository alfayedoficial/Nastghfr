package com.alialfayed.nstghfr.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.viewModel.HomeActivityViewModel
import com.ravikoradiya.library.CenterTitle


class HomeActivity : AppCompatActivity() {

    private lateinit var homeActivityViewModel:HomeActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeActivityViewModel = ViewModelProvider(this,HomeActivityFactory(this)).get(HomeActivityViewModel::class.java)
//        homeActivityViewModel.inflateFont()
        val toolbar = findViewById<Toolbar>(R.id.toolbar_HomeActivity)
        setSupportActionBar(toolbar)
        CenterTitle.centerTitle(toolbar,true)
        toolbar.setTitleTextAppearance(this,R.style.ToolbarStyle)
//        val actionBar = supportActionBar!!
//        // Set action bar elevation
//        actionBar.elevation = 6F
//
//        // Display the app icon in action bar/toolbar
//        actionBar.setDisplayShowHomeEnabled(true)


        homeActivityViewModel.inflateContainer()
        homeActivityViewModel.onItemSelectedNav()
    }

    internal class HomeActivityFactory(private var homeActivity: HomeActivity):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeActivityViewModel(homeActivity) as T
        }

    }
}
