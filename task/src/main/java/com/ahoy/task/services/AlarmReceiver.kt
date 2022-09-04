package com.ahoy.task.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("TAG", "onReceive: ")
        Toast.makeText(context, "BroadCast", Toast.LENGTH_SHORT).show()
        val service = Intent(context, NotificationService::class.java)
        service.data = Uri.parse("custom://" + System.currentTimeMillis())
        context?.startService(service)
    }

}