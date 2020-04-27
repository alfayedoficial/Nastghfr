package com.alialfayed.nstghfr.view.fragment


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.utils.AnlyzData.Companion.setLogcat
import com.alialfayed.nstghfr.utils.slat_alnabi_alarm.SlatAlnabiReceiver
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class NotificationFragment(private var activity: Activity) : Fragment() {

    private lateinit var item1Switch :Switch
    private lateinit var item2Switch :Switch
    private lateinit var item3Switch :Switch
    private lateinit var item4Switch :Switch
    private lateinit var item5Switch :Switch
    private lateinit var intent: Intent
    private lateinit var alarmManager1: AlarmManager
    private lateinit var intentAlarm1 :Intent
    private lateinit var  pendingIntent1 :PendingIntent
    private lateinit var alarmManager2: AlarmManager
    private lateinit var intentAlarm2 :Intent
    private lateinit var  pendingIntent2 :PendingIntent
    private lateinit var alarmManager3: AlarmManager
    private lateinit var intentAlarm3 :Intent
    private lateinit var  pendingIntent3 :PendingIntent
    private lateinit var alarmManager4: AlarmManager
    private lateinit var intentAlarm4 :Intent
    private lateinit var  pendingIntent4 :PendingIntent
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        activity.title = "التنبيهات"
        init(view)

        item1Switch.setOnCheckedChangeListener { _, isChecked ->
            intentAlarm1 = Intent(activity, SlatAlnabiReceiver::class.java)
            intentAlarm1.putExtra("SWITCHER","SWITCHER1")
            pendingIntent1 =PendingIntent.getBroadcast(activity.applicationContext, 1,intentAlarm1, PendingIntent.FLAG_CANCEL_CURRENT)
            if (isChecked){
                AnlyzData.setNotificationSWITCH1(activity,true)
                alarmManager1.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),60*1000,pendingIntent1)
            }else{
                alarmManager1.cancel(pendingIntent1);
                AnlyzData.setNotificationSWITCH1(activity,false)
            }
        }

        item2Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                intentAlarm2  = Intent(activity, SlatAlnabiReceiver::class.java)
                intentAlarm2.putExtra("SWITCHER", "SWITCHER2")
                pendingIntent2 = PendingIntent.getBroadcast( activity.applicationContext, 0, intentAlarm2, PendingIntent.FLAG_CANCEL_CURRENT)

                setAlarmNotification(0,30,pendingIntent2,alarmManager2,"00:30 AM")
                AnlyzData.setNotificationSWITCH2(activity, true)
            }else{
                AnlyzData.setNotificationSWITCH2(activity,false)
                cancelAlarmNotification(alarmManager2,pendingIntent2)
            }
        }

        item3Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                intentAlarm3  = Intent(activity, SlatAlnabiReceiver::class.java)
                intentAlarm3.putExtra("SWITCHER", "SWITCHER3")
                pendingIntent3 = PendingIntent.getBroadcast( activity.applicationContext, 0, intentAlarm3, PendingIntent.FLAG_CANCEL_CURRENT)

                setAlarmNotification(6,0,pendingIntent3,alarmManager3,"6:00 AM")
                AnlyzData.setNotificationSWITCH3(activity, true)
            }else{
                AnlyzData.setNotificationSWITCH3(activity,false)
                cancelAlarmNotification(alarmManager3,pendingIntent3)
            }
        }

        item4Switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                intentAlarm4  = Intent(activity, SlatAlnabiReceiver::class.java)
                intentAlarm4.putExtra("SWITCHER", "SWITCHER4")
                pendingIntent4 = PendingIntent.getBroadcast( activity.applicationContext, 0, intentAlarm4, PendingIntent.FLAG_CANCEL_CURRENT)

                setAlarmNotification(19,0,pendingIntent4,alarmManager4,"6:00 PM")
                AnlyzData.setNotificationSWITCH4(activity, true)
            }else{
                AnlyzData.setNotificationSWITCH4(activity,false)
                cancelAlarmNotification(alarmManager4,pendingIntent4)
            }
        }

        return view
    }


    @SuppressLint("SimpleDateFormat")
    private fun setAlarmNotification(hour:Int, minute:Int, pendingIntent:PendingIntent, alarmManager: AlarmManager,timeCheck:String){
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        val sdf = SimpleDateFormat("HH:mm a")
        val currentDate = sdf.format(Date())
        // Check if currentTime == alarm Time
//        if (currentDate >= timeCheck){
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1)
//        }else{
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
//        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND,0)

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_HOUR, pendingIntent)
        setLogcat("StepReceiver","Register Receiver")
    }

    private fun cancelAlarmNotification(alarmManager: AlarmManager , pendingIntent: PendingIntent){
        alarmManager.cancel(pendingIntent);
    }

    private fun init(view: View){
        alarmManager1 = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager2 = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager3 = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager4 = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        intent  = Intent(activity, SlatAlnabiReceiver::class.java)
        pendingIntent1 = PendingIntent.getBroadcast( activity.applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent2 = PendingIntent.getBroadcast( activity.applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent3 = PendingIntent.getBroadcast( activity.applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        pendingIntent4 = PendingIntent.getBroadcast( activity.applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)


        item1Switch = view.findViewById(R.id.item_1_Switch_NotificationFragment)
        item2Switch = view.findViewById(R.id.item_2_Switch_NotificationFragment)
        item3Switch = view.findViewById(R.id.item_3_Switch_NotificationFragment)
        item4Switch = view.findViewById(R.id.item_3_Switch_NotificationFragment)

        item1Switch.isChecked = AnlyzData.getNotificationSwitch1(activity)
        item2Switch.isChecked = AnlyzData.getNotificationSwitch2(activity)
        item3Switch.isChecked = AnlyzData.getNotificationSwitch3(activity)
        item4Switch.isChecked = AnlyzData.getNotificationSwitch4(activity)

    }




}
