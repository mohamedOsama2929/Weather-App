package com.ahoy.task.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.ahoy.core.base.activity.BaseActivity
import com.ahoy.task.R
import com.ahoy.task.services.AlarmReceiver
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


const val SIX_AM = 6

@AndroidEntryPoint
class TaskActivity : BaseActivity() {
    var id = 115
    var myIntent: Intent? = null
    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null
    override var navGraphResourceId: Int = R.navigation.task_navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWeatherAlarm()
    }

    private fun setWeatherAlarm() {
        myIntent = Intent(this, AlarmReceiver::class.java)
        myIntent!!.putExtra("id", id)
        pendingIntent = PendingIntent.getBroadcast(baseContext, id, myIntent, 0)

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, myIntent, 0)
        myIntent?.data = (Uri.parse("custom://" + System.currentTimeMillis()));
        alarmManager?.cancel(pendingIntent)
        val alarmStartTime = Calendar.getInstance()
        val now = Calendar.getInstance()
        alarmStartTime.set(Calendar.HOUR_OF_DAY, SIX_AM)
        alarmStartTime.set(Calendar.MINUTE, 0)
        alarmStartTime.set(Calendar.SECOND, 0)
        if (now.after(alarmStartTime)) {
            Log.e("Hey", "Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }
        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }
}