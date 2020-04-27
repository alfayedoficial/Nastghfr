package com.alialfayed.nstghfr.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setDefaultCounterType
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setReceiverTodayAlarm
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setTodayAlarm

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/27/2020 - 7:15 AM
 */
class ReceiverTodayAlarm:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        setDefaultCounterType(context, 0)
        setTodayAlarm(context)
        setReceiverTodayAlarm(context)
    }
}