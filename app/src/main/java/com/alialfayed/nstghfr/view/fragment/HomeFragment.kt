package com.alialfayed.nstghfr.view.fragment


import android.app.ActionBar
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alialfayed.nstghfr.R


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment(activity: Activity) : Fragment() {

    override fun onCreateView(  inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        activity!!.title = "الصفحة الرئيسية"



        return view
}


}
