package com.ahoy.task.services

import android.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.ahoy.task.ui.activity.TaskActivity


class NotificationService : Service() {

    private val CHANNEL_ID: String = "Weather APP"
    private var notificationManager: NotificationManager? = null
    private var pendingIntent: PendingIntent? = null
    private val NOTIFICATION_ID = 1
    private var notification: Notification? = null


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showNotification() {
        val context: Context = this.applicationContext
        notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val mIntent = Intent(this, TaskActivity::class.java)
        val bundle = Bundle()
        mIntent.putExtras(bundle)
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0)


        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.btn_plus)
                .setTicker("ticker value")
                .setAutoCancel(true)
                .setChannelId(CHANNEL_ID)
                .setContentTitle(getString(com.ahoy.task.R.string.weather_info))
                .setContentText(getString(com.ahoy.task.R.string.weather_details)).build()
        } else {
            notification = Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.btn_plus)
                .setTicker("ticker value")
                .setAutoCancel(true)
                .setContentTitle(getString(com.ahoy.task.R.string.weather_info))
                .setContentText(getString(com.ahoy.task.R.string.weather_details)).build()
        }
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(com.ahoy.task.R.string.channel_name)
            val descriptionText = getString(com.ahoy.task.R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager?.createNotificationChannel(mChannel)
        }
        notificationManager!!.notify(NOTIFICATION_ID, notification)
    }
}