package com.alialfayed.nstghfr.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.model.AstghfrModel

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/26/2020 - 12:00 AM
 */
class SpinnerCustomAdapter: ArrayAdapter<AstghfrModel> {

    private lateinit var astghfrModel: ArrayList<AstghfrModel>

    constructor(context: Context, resource: Int, astghfrModel: ArrayList<AstghfrModel>) : super(
        context,
        resource,
        astghfrModel
    ){this.astghfrModel = astghfrModel}

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return CustomSpinnerView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return CustomSpinnerView(position, convertView, parent)
    }

    private fun CustomSpinnerView(position: Int, convertView: View?, parent: ViewGroup) : View {
        //Getting the Layout
        val customView = LayoutInflater.from(context).inflate(R.layout.spinner_item_layout, parent, false)

        // Declaring and initalizing the spinnerTitle and idTextView
        val spinnerTitle = customView.findViewById<TextView>(R.id.SpinnerTitle)
        val idTextView = customView.findViewById<TextView>(R.id.spinnerID)


        val title = astghfrModel[position].getAnstghfrTitle()
        spinnerTitle.text = title
        idTextView.text = astghfrModel[position].getAnstghfrID()

//        if (title.length > 30){
//            val dot = " ... "
//            spinnerTitle.text = title+dot
//        }
        return customView
    }

}