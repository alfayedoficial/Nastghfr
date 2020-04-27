package com.alialfayed.nstghfr.view.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alialfayed.nstghfr.R
import com.alialfayed.nstghfr.utils.AnlyzData
import com.alialfayed.nstghfr.viewModel.HomeFrViewModel
import kotlinx.android.synthetic.main.activity_custom_dialog.*

class CustomDialog : AppCompatActivity() {

    private var basicCounter = 0
    private lateinit var ringtone: Ringtone
    private var soundFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notification)


        // to stop finish activity outSide
        this.setFinishOnTouchOutside(false)
        imgBtn_Exit.setOnClickListener {
            finish()
            ringtone.stop()
        }


        val inflaterDialog = intent.getStringExtra("SWITCHER")

        if (inflaterDialog == "SWITCHER") {
            var position = AnlyzData.getIndex(this)
            if (HomeFrViewModel.setData().size == position) {
                position = 0
                AnlyzData.setIndex(this, 0)
                txtTiltle_NotificationDialog.text =
                    HomeFrViewModel.setData()[position].getAnstghfrTitle()
            } else {
                txtTiltle_NotificationDialog.text =
                    HomeFrViewModel.setData()[position].getAnstghfrTitle()
            }

            imgBtnCounter_NotificationDialog.setOnClickListener {
                basicCounter++
                AnlyzData.setCounterType(this, 1)
                AnlyzData.setIndex(this, 1)
                finish()
                ringtone.stop()
            }
        } else if (inflaterDialog == "SWITCHER1") {
            txtTiltle_NotificationDialog.text = "صل على النبى محمد صل الله عليه وسلم"
            imgBtnCounter_NotificationDialog.setOnClickListener {
                basicCounter++
                AnlyzData.setCounterType(this, 1)
                finish()
                ringtone.stop()
                Log.i("aaaaaaaaaaaaaaaaa", "Dialog Switcher1")

            }
        } else if (inflaterDialog == "SWITCHER2") {
            playAlarm()
            txtTiltle_NotificationDialog.text =getString(R.string.switch2)
            imgBtnCounter_NotificationDialog.setOnClickListener {
                finish()
                ringtone.stop()
            }
        } else if (inflaterDialog == "SWITCHER3"){
            playAlarm()
        }

    }

    private fun playAlarm() {
        ringtone.play()
        imgBtn_Sound.visibility = View.VISIBLE
        imgBtn_Sound.setOnClickListener {
            soundFlag = if (soundFlag){
                ringtone.stop()
                imgBtn_Sound.setImageResource(R.drawable.ic_mute)
                false
            }else{
                ringtone.play()
                imgBtn_Sound.setImageResource(R.drawable.ic_speaker)
                true
            }
        }
    }

}
