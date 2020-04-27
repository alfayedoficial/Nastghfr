package com.alialfayed.nstghfr.utils.screen.foregroundService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.view.activity.HomeActivity

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 4/5/2020 - 3:47 PM
 */
@Suppress("UNREACHABLE_CODE")
class ForegroundServiceDialog:Service() {

    private var requestId: String = "1"
    private var foregroundServiceReceiver: ForegroundServiceReceiver? = null
    private val message = "هذا الإشعار مهم"
    private val descriptorMessage = "لا تقم بحذف هذا الإشعار حتى تستمر فى الاستغفار"
    private val CHANNEL_ID = "ForegroundServiceChannel"
    private var uniqueInt = (System.currentTimeMillis() and 0xfffffff).toInt()
    private var inflaterIntent :Intent? = null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        inflaterIntent = intent
        val inflaterDialog = intent.getStringExtra("SWITCHER")
        if (inflaterDialog == "SWITCHER"){
            // Create an IntentFilter instance.
            val intentFilter = IntentFilter()

            // Add network connectivity change action.
            intentFilter.addAction("android.intent.action.SCREEN_ON")
            intentFilter.addAction("android.intent.action.SCREEN_OFF")

            // Set broadcast receiver priority.
            intentFilter.priority = 100

            // Create a network change broadcast receiver.
            foregroundServiceReceiver = ForegroundServiceReceiver()

            // Register the broadcast receiver with the intent filter object.
            registerReceiver(foregroundServiceReceiver, intentFilter)

            Log.i(ForegroundServiceReceiver.SCREEN_TOGGLE_TAG,
                "Service onCreate: screenOnOffReceiver is registered."
            )
        }else if (inflaterDialog == "SWITCHER1"){

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

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister screenOnOffReceiver when destroy.
        if (foregroundServiceReceiver != null) {
            unregisterReceiver(foregroundServiceReceiver)
            Log.i(ForegroundServiceReceiver.SCREEN_TOGGLE_TAG,
                "Service onDestroy: screenOnOffReceiver is unregistered." )
        }
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