package com.alialfayed.nstghfr.utils.slat_alnabi_alarm

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.view.activity.HomeActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 4/4/2020 - 8:48 AM
 */
class SlatAlnabiService : IntentService("MyService") {
    private val message = "هذا الإشعار مهم"
    private val descriptorMessage = "لا تقم بحذف هذا الإشعار حتى تستمر فى الاستغفار"
    private val CHANNEL_ID = "ForegroundServiceChannel"
    private var uniqueInt = (System.currentTimeMillis() and 0xfffffff).toInt()

    private val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val intentAlarm = Intent(this, SlatAlnabiReceiver::class.java)
    private val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intentAlarm, PendingIntent.FLAG_CANCEL_CURRENT)


    override fun onHandleIntent(intent: Intent?) {
        if (AnlyzData.getNotificationSwitch1(this) && intent!!.getStringExtra("SWITCHER") =="SWITCHER1") {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(this, SlatAlnabiReceiver::class.java)
            alarmIntent.putExtra("SWITCHER","SWITCHER1")
            Log.i("aaaaaaaaaaaaaaaaa", "Service Intent Switcher1")
            val pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_ONE_SHOT)
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),60*60*1000,pendingIntent)
        }
        if (AnlyzData.getNotificationSwitch2(this) && intent!!.getStringExtra("SWITCHER") == "SWITCHER2") {
            setAlarm(11,20,"SWITCHER2","00:30 AM")
        }
        if (AnlyzData.getNotificationSwitch3(this) && intent!!.getStringExtra("SWITCHER") == "SWITCHER3") {
            setAlarm(6,0,"SWITCHER3","6:00 AM")
        }
        if (AnlyzData.getNotificationSwitch4(this) && intent!!.getStringExtra("SWITCHER4") != null) {
            setAlarm(19,0,"SWITCHER4","6:00 PM")
        }

        createNotificationChannel()
        val notification = Intent(applicationContext, HomeActivity::class.java)
        notification.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(applicationContext, uniqueInt, notification, PendingIntent.FLAG_ONE_SHOT)

        val notification1 = NotificationCompat.Builder(
            applicationContext,CHANNEL_ID)
            .setContentTitle(getText(R.string.app_name))
            .setContentText(message + "\n"+ descriptorMessage )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification1)

    }

    private fun setAlarm(hour:Int, minute:Int, putStingIntent:String ,timeCheck:String){
        intentAlarm.putExtra("SWITCHER", putStingIntent)

        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        val sdf = SimpleDateFormat("HH:mm a")
        val currentDate = sdf.format(Date())

        if (currentDate >= timeCheck){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1)
        }else{
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND,0)

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,AlarmManager.INTERVAL_HOUR, pendingIntent)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID,"Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}