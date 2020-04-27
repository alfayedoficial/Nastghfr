package com.alialfayed.nstghfr.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setDefaultMonthCounterType
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setTodayAlarm
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setReceiverWeekAlarm

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/27/2020 - 7:15 AM
 */
class ReceiverMonthAlarm:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
//        Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
//        Log.i("Ahmed","Done")
        setDefaultMonthCounterType(context,0)
        setTodayAlarm(context)
        setReceiverWeekAlarm(context)

    }
}