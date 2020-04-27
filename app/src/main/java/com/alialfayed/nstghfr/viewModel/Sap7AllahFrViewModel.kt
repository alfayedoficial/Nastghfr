package com.alialfayed.nstghfr.viewModel

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.view.adapter.SpinnerCustomAdapter
import com.alialfayed.nstghfr.view.fragment.Sap7AllahFragment

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/27/2020 - 3:22 AM
 */
class Sap7AllahFrViewModel(private var sap7AllahFragment: Sap7AllahFragment,private var activity: Activity) :ViewModel() {

    private var basicCounter = 0

    fun initSpiner(spinnerSap7Fragment: Spinner, spinnerPosition: Int , txtCounter: TextView) {
        val adapter = SpinnerCustomAdapter(activity, R.layout.spinner_item_layout,HomeFrViewModel.setData())
        spinnerSap7Fragment.adapter = adapter
        spinnerSap7Fragment.setSelection(spinnerPosition)

        spinnerSap7Fragment.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                basicCounter = 0
                txtCounter.text = basicCounter.toString()
            }

        }
    }

    fun initCounter(btnSap7Allah: ImageButton, txtCounter: TextView) {
        btnSap7Allah.setOnClickListener {
            basicCounter++
            txtCounter.text = basicCounter.toString()
            AnlyzData.setCounterType(activity, 1)
        }
    }
}