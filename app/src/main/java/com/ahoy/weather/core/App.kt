package com.ahoy.weather.core

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.multidex.MultiDex
import com.ahoy.task.services.AlarmReceiver
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import java.util.*


@HiltAndroidApp
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        //setAlarm()
    }

    private fun setAlarm() {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, AlarmReceiver::class.java)

        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE)
        alarmIntent.data = (Uri.parse("custom://" + System.currentTimeMillis()));
        alarmManager.cancel(pendingIntent)
        val alarmStartTime = Calendar.getInstance()
        val now = Calendar.getInstance()
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 15)
        alarmStartTime.set(Calendar.MINUTE, 4)
        alarmStartTime.set(Calendar.SECOND, 0)
        if (now.after(alarmStartTime)) {
            Log.e("Hey", "Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        /*  alarmManager.setInexactRepeating(
              AlarmManager.ELAPSED_REALTIME_WAKEUP,
              SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
              AlarmManager.INTERVAL_HALF_HOUR,
              pendingIntent
          )*/

        Log.e("Alarm", "Alarms set for everyday 6 am.");
    }
}