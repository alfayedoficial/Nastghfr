package com.alialfayed.nstghfr.view.fragment


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.view.activity.HomeActivity

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment(activity: Activity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        activity!!.title = "الإعدادت"



        return view
    }



}
