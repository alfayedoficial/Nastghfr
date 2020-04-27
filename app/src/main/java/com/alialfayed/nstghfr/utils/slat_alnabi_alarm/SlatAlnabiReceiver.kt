package com.alialfayed.nstghfr.utils.slat_alnabi_alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.view.activity.CustomDialog
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 4/4/2020 - 8:46 AM
 */
class SlatAlnabiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        AnlyzData.setLogcat("StepReceiver", "Login Receiver")

        if (AnlyzData.getNotificationSwitch1(context) && intent.getStringExtra("SWITCHER") == "SWITCHER1"){
            if (intent.action != null && intent.action == "android.intent.action.BOOT_COMPLETED") {
                goToService(context,"SWITCHER1")
            } else {
                geToReceiver(context,"SWITCHER1")
            }
        }
        if (AnlyzData.getNotificationSwitch2(context) && intent.getStringExtra("SWITCHER") == "SWITCHER2"){
            AnlyzData.setLogcat("StepReceiver", "true Receiver")

            if (intent.action != null && intent.action == "android.intent.action.BOOT_COMPLETED") {
                AnlyzData.setLogcat("StepReceiver", "Register Service")

                goToService(context,"SWITCHER2")
            } else {
                AnlyzData.setLogcat("StepReceiver", "Register Receiver2")

                geToReceiver(context,"SWITCHER2")
                setAlarmNotification(0,30,context,"00:30 AM","SWITCHER2")
            }
        }
        if (AnlyzData.getNotificationSwitch3(context) && intent.getStringExtra("SWITCHER") == "SWITCHER3"){
            if (intent.action != null && intent.action == "android.intent.action.BOOT_COMPLETED") {
                goToService(context,"SWITCHER3")
            } else {
                geToReceiver(context,"SWITCHER3")
                setAlarmNotification(6,0,context,"6:00 AM","SWITCHER3")
            }
        }
        if (AnlyzData.getNotificationSwitch4(context) && intent.getStringExtra("SWITCHER") == "SWITCHER4"){
            if (intent.action != null && intent.action == "android.intent.action.BOOT_COMPLETED") {
                goToService(context,"SWITCHER4")
            } else {
                geToReceiver(context,"SWITCHER4")
                setAlarmNotification(19,0,context,"6:00 PM","SWITCHER4")
            }
        }
    }

    private fun goToService(context: Context,switcher:String){
        val serviceIntent = Intent(context, SlatAlnabiService::class.java)
        serviceIntent.putExtra("SWITCHER",switcher)
        context.startService(serviceIntent)
        Log.i("aaaaaaaaaaaaaaaaa", "service - $switcher")
    }

    private fun geToReceiver(context: Context,switcher: String){
        val intentDialog = Intent(context, CustomDialog::class.java)
        intentDialog.putExtra("SWITCHER",switcher)
        intentDialog.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        context.startActivity(intentDialog)
        Log.i("aaaaaaaaaaaaaaaaa", "Receiver - $switcher")

    }

    @SuppressLint("SimpleDateFormat")
    private fun setAlarmNotification(hour:Int, minute:Int,context: Context, timeCheck:String,checkIntent:String){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intentAlarm = Intent(context, SlatAlnabiReceiver::class.java)
        intentAlarm.putExtra("SWITCHER",checkIntent)
        val pendingIntent =PendingIntent.getBroadcast(context.applicationContext, 1,intentAlarm, PendingIntent.FLAG_CANCEL_CURRENT)


        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        val sdf = SimpleDateFormat("HH:mm a")
        val currentDate = sdf.format(Date())
        // Check if currentTime == alarm Time
        if (currentDate >= timeCheck){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1)
        }else{
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND,0)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_HOUR, pendingIntent)
        AnlyzData.setLogcat("StepReceiver", "Register Receiver")
    }

}