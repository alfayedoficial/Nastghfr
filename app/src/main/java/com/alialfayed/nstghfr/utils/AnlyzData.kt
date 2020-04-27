package com.alialfayed.nstghfr.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import java.util.*
import kotlin.math.log


/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 3/27/2020 - 6:37 AM
 */

class AnlyzData {
    companion object{
        private const val TODAY_COUNTER = "COUNTER1"
        private const val WEEK_COUNTER = "COUNTER2"
        private const val MONTH_COUNTER = "COUNTER3"
        private const val TOTAL_COUNTER = "COUNTER4"
        private const val ALARM_TODAY = "ALARM_TODAY"
        private const val ALARM_WEEK = "ALARM_WEEK"
        private const val ALARM_MONTH = "ALARM_MONTH"
        private const val RECEIVER_FLAG = "RECEIVER_FLAG"
        private const val INDEX = "INDEX"
        private const val SWITCH_1 = "SWITCH_1"
        private const val SWITCH_2 = "SWITCH_2"
        private const val SWITCH_3 = "SWITCH_3"
        private const val SWITCH_4 = "SWITCH_4"
        private const val SWITCH_5 = "SWITCH_5"
        var receiverFlag = false

        private fun preferences(context: Context):SharedPreferences{
            return context.getSharedPreferences("counter",0)
        }
        fun setDefaultCounterType(context: Context,todayCounter:Long){
            preferences(context).edit()
                .putLong(TODAY_COUNTER,todayCounter)
                .apply()
        }
        fun setDefaultWeekCounterType(context: Context,weekCounter:Long){
            preferences(context).edit()
                .putLong(WEEK_COUNTER,weekCounter)
                .apply()
        }
        fun setDefaultMonthCounterType(context: Context,monthCounter:Long){
            preferences(context).edit()
                .putLong(MONTH_COUNTER,monthCounter)
                .apply()
        }
        fun setCounterType(context: Context,todayCounter:Long){
            val today = getTodayCounterType(context)+todayCounter
            val week = getWeekCounterType(context) + todayCounter
            val month = getMonthCounterType(context) + todayCounter
            val total = getTotalCounterType(context) + todayCounter
            preferences(context).edit()
                .putLong(TODAY_COUNTER,today)
                .putLong(WEEK_COUNTER,week)
                .putLong(MONTH_COUNTER,month)
                .putLong(TOTAL_COUNTER,total)
                .apply()
        }
        fun getTodayCounterType(context: Context):Long{
            return preferences(context).getLong(TODAY_COUNTER,0)
        }
        fun getWeekCounterType(context: Context):Long{
            return preferences(context).getLong(WEEK_COUNTER,0)
        }
        fun getMonthCounterType(context: Context):Long{
            return preferences(context).getLong(MONTH_COUNTER,0)
        }
        fun getTotalCounterType(context: Context):Long{
            return preferences(context).getLong(TOTAL_COUNTER,0)
        }


        fun setReceiverFlag(context: Context,receiverFlag:Boolean){
            preferences(context).edit()
                .putBoolean(RECEIVER_FLAG,receiverFlag)
                .apply()
        }
        fun getReceiverFlag(context: Context):Boolean{
            return preferences(context).getBoolean(RECEIVER_FLAG,false)
        }




        // Alarm Receiver
        fun setReceiverTodayAlarm(activity: Context){
            val intent = Intent(activity, ReceiverTodayAlarm::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                activity.applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)

            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE , 0)
            calendar.set(Calendar.SECOND,0)

            // shared pref ==> 1585346400124 + (24 * 60 * 60 * 1000) (7 * 24 * 60 * 60 *1000)

            // Log.i("Ahmed", ""+calendar.timeInMillis)
            val alarmManager: AlarmManager = activity.getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getTodayAlarm(activity),AlarmManager.INTERVAL_HOUR, pendingIntent)
        }
        fun setTodayAlarm(context: Context){
            val calender = getTodayAlarm(context) + (24 * 60 * 60 * 1000)
            preferences(context).edit()
                .putString(ALARM_TODAY,calender.toString())
                .apply()
        }
        fun getTodayAlarm(context: Context):Long{
            return preferences(context).getString(ALARM_TODAY,"1585346400124")!!.toLong()
        }
        fun setReceiverWeekAlarm(activity: Context){
            val intent = Intent(activity, ReceiverTodayAlarm::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                activity.applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)

            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE , 0)
            calendar.set(Calendar.SECOND,0)

            val alarmManager: AlarmManager = activity.getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, getWeekAlarmAlarm(activity),AlarmManager.INTERVAL_HOUR, pendingIntent)
        }
        fun setWeekAlarm(context: Context){
            val calender = getWeekAlarmAlarm(context) + (7 * 24 * 60 * 60 *1000)
            preferences(context).edit()
                .putString(ALARM_WEEK,calender.toString())
                .apply()
        }
        fun getWeekAlarmAlarm(context: Context):Long{
            return preferences(context).getString(ALARM_WEEK,"1585346400124")!!.toLong()
        }


        // Alarm Receiver
        fun setIndex(context: Context,index:Int){
            var indexCounter =index
            if (index != 0){
                 indexCounter = getIndex(context)+index
            }
            preferences(context).edit()
                .putInt(INDEX,indexCounter)
                .apply()
        }
        fun getIndex(context: Context):Int{
            return preferences(context).getInt(INDEX,0)
        }


        // Alarm Notification
        fun setNotificationSWITCH1(context: Context,notification:Boolean){
            preferences(context).edit()
                .putBoolean(SWITCH_1,notification)
                .apply()
        }
        fun setNotificationSWITCH2(context: Context,notification:Boolean){
            preferences(context).edit()
                .putBoolean(SWITCH_2,notification)
                .apply()
        }
        fun setNotificationSWITCH3(context: Context,notification:Boolean){
            preferences(context).edit()
                .putBoolean(SWITCH_3,notification)
                .apply()
        }
        fun setNotificationSWITCH4(context: Context,notification:Boolean){
            preferences(context).edit()
                .putBoolean(SWITCH_4,notification)
                .apply()
        }
        fun setNotificationSWITCH5(context: Context,notification:Boolean){
            preferences(context).edit()
                .putBoolean(SWITCH_5,notification)
                .apply()
        }
        fun getNotificationSwitch1(context: Context):Boolean{
            return preferences(context).getBoolean(SWITCH_1,false)
        }
        fun getNotificationSwitch2(context: Context):Boolean{
            return preferences(context).getBoolean(SWITCH_2,false)
        }
        fun getNotificationSwitch3(context: Context):Boolean{
            return preferences(context).getBoolean(SWITCH_3,false)
        }
        fun getNotificationSwitch4(context: Context):Boolean{
            return preferences(context).getBoolean(SWITCH_4,false)
        }
        fun getNotificationSwitch5(context: Context):Boolean{
            return preferences(context).getBoolean(SWITCH_5,false)
        }


        fun setLogcat(logKey:String,message :String){
            Log.i(logKey,message)
        }
    }
}