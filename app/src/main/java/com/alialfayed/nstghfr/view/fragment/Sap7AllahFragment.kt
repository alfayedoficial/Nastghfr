package com.alialfayed.nstghfr.view.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.view.activity.HomeActivity
import com.alialfayed.nstghfr.viewModel.Sap7AllahFrViewModel

/**
 * A simple [Fragment] subclass.
 */
class Sap7AllahFragment(private var activity: HomeActivity,private var spinnerPosition: Int,private var toolbar: Toolbar
) : Fragment() {

    private lateinit var sap7AllahFrViewModel: Sap7AllahFrViewModel
    private lateinit var spinnerSap7Fragment : Spinner
    private lateinit var btnSap7Allah: ImageButton
    private lateinit var txtCounter: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sap7_allah, container, false)
        sap7AllahFrViewModel = ViewModelProvider(this,Sap7AllahFragmentFactory(this,activity)).get(Sap7AllahFrViewModel::class.java)
        spinnerSap7Fragment = view.findViewById(R.id.spinner_Sap7Fragment)
        btnSap7Allah = view.findViewById(R.id.imgBtnOnClick_Sap7Fragment)
        txtCounter = view.findViewById(R.id.txtCounter_Sap7Fragment)

        inflateToolbar()
        sap7AllahFrViewModel.initSpiner(spinnerSap7Fragment , spinnerPosition,txtCounter)
        sap7AllahFrViewModel.initCounter(btnSap7Allah,txtCounter)
        return view
    }

    private fun inflateToolbar() {

        activity.title = "الصفحة الرئيسية"
        val actionBar = (activity as AppCompatActivity).supportActionBar!!
        // Set action bar elevation
        actionBar.elevation = 6F

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_back)

        toolbar.setNavigationOnClickListener {
            val fragmentTransaction =fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,HomeFragment(activity,toolbar))
            fragmentTransaction.commit()
        }
    }


    internal class Sap7AllahFragmentFactory(private var sap7AllahFragment: Sap7AllahFragment , private var activity: Activity):
        ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Sap7AllahFrViewModel(sap7AllahFragment , activity) as T
        }
    }


}
