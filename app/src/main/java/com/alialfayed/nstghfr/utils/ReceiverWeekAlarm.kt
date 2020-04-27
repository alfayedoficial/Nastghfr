package com.alialfayed.nstghfr.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setDefaultWeekCounterType
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setReceiverTodayAlarm
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setWeekAlarm

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/27/2020 - 7:15 AM
 */
class ReceiverWeekAlarm:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
//        Toast.makeText(context,"done",Toast.LENGTH_SHORT).show()
//        Log.i("Ahmed","Done")
        setDefaultWeekCounterType(context,0)
        setWeekAlarm(context)
        setReceiverTodayAlarm(context)
    }
}