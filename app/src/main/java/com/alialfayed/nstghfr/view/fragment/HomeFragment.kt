package com.alialfayed.nstghfr.view.fragment


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.view.activity.HomeActivity
import com.alialfayed.nstghfr.viewModel.HomeFrViewModel


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment(
    private var activity: HomeActivity,
    private var toolbar: Toolbar
) : Fragment() {

    private lateinit var homeFrViewModel: HomeFrViewModel
    private lateinit var spinnerHomefragment :Spinner
    private lateinit var btnAstghfrAllah:Button
    private lateinit var btnSap7Allah:Button
    private lateinit var btnTaspi7AfterSalah :Button
    private lateinit var txtToday:TextView
    private lateinit var txtWeek:TextView
    private lateinit var txtMonth:TextView
    private lateinit var txtTotal:TextView
    companion object{private const val PERMISSION_CODE = 0}

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeFrViewModel = ViewModelProvider(this,HomeFragmentFactory(this,activity)).get(HomeFrViewModel::class.java)
        inflateToolbar()

        spinnerHomefragment = view.findViewById(R.id.spinner_HomeFragment)
        btnAstghfrAllah = view.findViewById(R.id.btnAstghfrAllah_HomeFragment)
        btnSap7Allah = view.findViewById(R.id.btnSap7_HomeFragment)
        btnTaspi7AfterSalah = view.findViewById(R.id.btnTaspi7AfterSalah_HomeFragment)
        txtToday = view.findViewById(R.id.txtToday_HomeFragment)
        txtWeek = view.findViewById(R.id.txtWeek_HomeFragment)
        txtMonth = view.findViewById(R.id.txtMonth_HomeFragment)
        txtTotal = view.findViewById(R.id.txtTotal_HomeFragment)

        if (checkPermission(android.Manifest.permission.FOREGROUND_SERVICE)
            && checkPermission(android.Manifest.permission.INTERNET)
            && checkPermission(android.Manifest.permission.WAKE_LOCK)
            && checkPermission(android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            && checkPermission(android.Manifest.permission.RECEIVE_BOOT_COMPLETED)){
        }else{
            takePermission()
        }

        homeFrViewModel.initSpiner(spinnerHomefragment)
        homeFrViewModel.initButtom(btnAstghfrAllah,btnSap7Allah,toolbar,btnTaspi7AfterSalah)
        homeFrViewModel.initAnalyz(txtToday,txtWeek,txtMonth,txtTotal)



        return view
    }

    internal class HomeFragmentFactory(private var homeFragment: HomeFragment , private var activity: HomeActivity):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeFrViewModel(homeFragment , activity) as T
        }
    }


    private fun inflateToolbar() {

        activity.title = "الصفحة الرئيسية"
        val actionBar = (activity as AppCompatActivity).supportActionBar!!
        // Set action bar elevation
        actionBar.elevation = 6F

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(false)
        actionBar.setDisplayHomeAsUpEnabled(false)

    }


    fun checkPermission(permission: String): Boolean {
        val check: Int = ContextCompat.checkSelfPermission(activity, permission)
        return (check == PackageManager.PERMISSION_GRANTED)
    }

    // take Permission
    fun takePermission(){
        val arrayPermission = arrayOf(Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.INTERNET,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Manifest.permission.RECEIVE_BOOT_COMPLETED)
        ActivityCompat.requestPermissions(activity, arrayPermission, PERMISSION_CODE)
    }

}
