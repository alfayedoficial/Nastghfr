package com.alialfayed.nstghfr.viewModel

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.model.AstghfrModel
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.getMonthCounterType
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.getTodayCounterType
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.getTotalCounterType
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.getWeekCounterType
import com.alialfayed.nstghfr.utils.screen.foregroundService.ForegroundServiceDialog
import com.alialfayed.nstghfr.utils.screen.receiver.ScreenOnOffBackgroundService
import com.alialfayed.nstghfr.utils.screen.receiver.ScreenOnOffReceiver
import com.alialfayed.nstghfr.view.activity.HomeActivity
import com.alialfayed.nstghfr.view.adapter.SpinnerCustomAdapter
import com.alialfayed.nstghfr.view.fragment.HomeFragment
import com.alialfayed.nstghfr.view.fragment.Sap7AllahFragment
import com.alialfayed.nstghfr.view.fragment.Taspi7AfterSalahFragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/25/2020 - 10:48 PM
 */
class HomeFrViewModel(private var homeFragment: HomeFragment, private var activity: HomeActivity):ViewModel() {

    private var spinnerPosition:Int = 0

    companion object{
        const val MY_PERMISSIONS_REQUEST_WRITE_SETTINGS = 0
        fun setData():ArrayList<AstghfrModel>{
            val astghfrModel: ArrayList<AstghfrModel> = ArrayList()
            astghfrModel.add(AstghfrModel("1","استغفر الله","استغفر الله"))
            astghfrModel.add(AstghfrModel("2","سبحان الله و بحمده","اسبحان الله و بحمده"))
            astghfrModel.add(AstghfrModel("3","لا إله اله الله محمد رسول الله","لا إله اله الله محمد رسول الله"))
            astghfrModel.add(AstghfrModel("4","لا اله الا الله سبحانك انى كنت من الظالمين","لا اله الا الله سبحانك انى كنت من الظالمين"))
            astghfrModel.add(AstghfrModel("4","لا اله الا الله سبحانك انى كنت من الظالمي اله الا الله سبحانك انى كنت من الظالمي اله الا الله سبحانك انى كنت من الظالمين","لا اله الا الله سبحانك انى كنت من الظالمين"))

            return astghfrModel
        }

    }

    fun initSpiner(spinnerHomeFragment: Spinner) {

        val adapter = SpinnerCustomAdapter(activity, R.layout.spinner_item_layout,setData())
        spinnerHomeFragment.adapter = adapter
        spinnerHomeFragment.setSelection(0)


        // OnclickSpinner
        spinnerHomeFragment.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val clickItem = adapterView?.getItemAtPosition(position)
                spinnerPosition = position
            }

        }

    }

    fun initButtom(
        btnAstghfrAllah: Button,
        btnSap7: Button,
        toolbar: Toolbar,
        btnTaspi7AfterSalah: Button
    ) {

        btnAstghfrAllah.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val backgroundService = Intent(activity, ForegroundServiceDialog::class.java)
                backgroundService.putExtra("SWITCHER","SWITCHER")
                activity.startForegroundService(backgroundService)
                Log.i(ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "Activity onCreate ForegroundServiceDialog")
                snackbar()
                Toast.makeText(activity,"ForegroundServiceDialog",Toast.LENGTH_LONG).show()
            }else{
                val backgroundService = Intent(activity, ScreenOnOffBackgroundService::class.java)
                backgroundService.putExtra("SWITCHER","SWITCHER")
                activity.startService(backgroundService)
                Log.d(ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "Activity onCreate")
                snackbar()
            }

        }
        btnSap7.setOnClickListener {
            val fragmentTransaction =homeFragment.fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,Sap7AllahFragment(activity,spinnerPosition,toolbar))
            fragmentTransaction.commit()
        }
        btnTaspi7AfterSalah.setOnClickListener {
            val fragmentTransaction =homeFragment.fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.containerFrameLayout_HomeActivity,Taspi7AfterSalahFragment(activity,toolbar))
            fragmentTransaction.commit()
        }
    }

    fun initAnalyz(
        txtToday: TextView,
        txtWeek: TextView,
        txtMonth: TextView,
        txtTotal: TextView
    ) {
        txtToday.text = getTodayCounterType(activity).toString()
        txtWeek.text  = getWeekCounterType(activity).toString()
        txtMonth.text = getMonthCounterType(activity).toString()
        txtTotal.text = getTotalCounterType(activity).toString()
    }


    fun snackbar(){
        Snackbar.make(activity.findViewById(android.R.id.content),
           "تم تفعيل خدمة نستغفر",
            Snackbar.LENGTH_SHORT
        ).show()
    }


    fun checkPermission(): Boolean {
        val check: Int = ContextCompat.checkSelfPermission(activity, "com.huawei.android.launcher.permission.WRITE_SETTINGS")
        return (check == PackageManager.PERMISSION_GRANTED)
    }

    // take Permission
    fun takePermission(){
        val arrayPermission = arrayOf("com.huawei.android.launcher.permission.WRITE_SETTINGS")
        ActivityCompat.requestPermissions(activity, arrayPermission, MY_PERMISSIONS_REQUEST_WRITE_SETTINGS)
    }






}