package com.alialfayed.nstghfr.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.getReceiverFlag
import com.alialfayed.nstghfr.utils.screen.receiver.ScreenOnOffReceiver
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
        toolbar.setTitleTextAppearance(this, R.style.ToolbarStyle)

        // Flag first time
        if (!getReceiverFlag(this)) {
            Toast.makeText(this,"false",Toast.LENGTH_SHORT).show()
            AnlyzData.setReceiverTodayAlarm(this)
            AnlyzData.setReceiverWeekAlarm(this)
            AnlyzData.setReceiverFlag(this,true)
        }

        homeActivityViewModel.inflateContainer(toolbar)
        homeActivityViewModel.onItemSelectedNav(toolbar)

    }

    internal class HomeActivityFactory(private var homeActivity: HomeActivity):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeActivityViewModel(homeActivity) as T
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "Activity onDestroy")
    }
}
