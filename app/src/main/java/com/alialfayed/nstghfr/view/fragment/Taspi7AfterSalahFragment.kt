package com.alialfayed.nstghfr.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.view.activity.HomeActivity
import com.alialfayed.nstghfr.viewModel.Taspi7AfterSalahFrViewModel

/**
 * A simple [Fragment] subclass.
 */
class Taspi7AfterSalahFragment(
    private var activity: HomeActivity,
    private var toolbar: Toolbar
) : Fragment() {

    private lateinit var taspi7AfterSalahFrViewModel:Taspi7AfterSalahFrViewModel
    private lateinit var txtText : TextView
    private lateinit var txtBasicCounter : TextView
    private lateinit var txtCounter :TextView
    private lateinit var imgBtnOnClick:ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_taspi7_after_salah, container, false)
        taspi7AfterSalahFrViewModel = ViewModelProvider(this,Taspi7AfterSalahFactory(this , activity)).get(Taspi7AfterSalahFrViewModel::class.java)
        txtText = view.findViewById(R.id.txtText_Taspi7AfterSalah)
        txtBasicCounter = view.findViewById(R.id.txtBasicCounter_Taspi7AfterSalah)
        txtCounter = view.findViewById(R.id.txtCounter_Taspi7AfterSalah)
        imgBtnOnClick = view.findViewById(R.id.imgBtnOnClick_Taspi7AfterSalah)
        inflateToolbar()
        taspi7AfterSalahFrViewModel.btnOnclickCounter(txtText , txtBasicCounter , txtCounter , imgBtnOnClick)



        return view
    }

    internal class Taspi7AfterSalahFactory(private var Taspi7AfterSalah: Taspi7AfterSalahFragment , private var activity: HomeActivity):
        ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Taspi7AfterSalahFrViewModel(Taspi7AfterSalah , activity) as T
        }
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

}
