package com.alialfayed.nstghfr.viewModel

import android.graphics.Typeface
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.view.activity.HomeActivity
import com.alialfayed.nstghfr.view.fragment.HomeFragment
import com.alialfayed.nstghfr.view.fragment.InfoFragment
import com.alialfayed.nstghfr.view.fragment.NotificationFragment
import com.alialfayed.nstghfr.view.fragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_home.*
import me.ibrahimsn.lib.OnItemSelectedListener

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/13/2020 - 2:58 AM
 */
class HomeActivityViewModel(private var homeActivity: HomeActivity) : ViewModel() {

    fun inflateContainer(toolbar: Toolbar) {
        val fragmentTransaction = homeActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.containerFrameLayout_HomeActivity, HomeFragment(homeActivity,toolbar))
        fragmentTransaction.commit()
    }

    fun onItemSelectedNav(toolbar: Toolbar) {
        homeActivity.bottomBar_HomeActivity.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelect(pos: Int) {
                when (pos) {
                    0 -> {
                        val fragmentTransaction =homeActivity.supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,HomeFragment(homeActivity , toolbar))
                        fragmentTransaction.commit()
                    }
                    1 -> {
                        val fragmentTransaction =homeActivity.supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,NotificationFragment(homeActivity))
                        fragmentTransaction.commit()
                    }
                    2 -> {
                        val fragmentTransaction =homeActivity.supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,SettingsFragment(homeActivity))
                        fragmentTransaction.commit()
                    }
                    3 -> {
                        val fragmentTransaction =homeActivity.supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,InfoFragment(homeActivity))
                        fragmentTransaction.commit()
                    }
                }
            }
        })
    }

    fun inflateFont() {
        val typeFace = Typeface.createFromAsset(homeActivity.assets,"ayman15")!!
    }

}