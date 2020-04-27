package com.alialfayed.nstghfr.utils.screen.foregroundService

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.alialfayed.nstghfr.view.activity.CustomDialog

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 4/5/2020 - 3:51 PM
 */
class ForegroundServiceReceiver : BroadcastReceiver() {
    companion object{
        const val SCREEN_TOGGLE_TAG = "SCREEN_TOGGLE_TAG"
    }

    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action
        if (Intent.ACTION_SCREEN_OFF == action) {
            Log.i(SCREEN_TOGGLE_TAG, "Screen is turn off.")
        } else if (Intent.ACTION_SCREEN_ON == action) {
            Log.i(SCREEN_TOGGLE_TAG, "Screen is turn on.")

            val intentDialog = Intent(context, CustomDialog::class.java)
            intentDialog.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            intentDialog.putExtra("SWITCHER","SWITCHER")
            context.startActivity(intentDialog)
            // workManger
            // for backgroundSERVICE
        }

    }
}