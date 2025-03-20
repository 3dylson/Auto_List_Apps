package com.example.autolistapps.presentation

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.autolistapps.R
import com.example.autolistapps.presentation.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NotificationHelper @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "new_apps_channel"
        private const val CHANNEL_NAME = "New Apps"
        private const val NOTIFICATION_ID = 1001
    }

    init {
        createNotificationChannel()
    }

    fun showNewAppsNotification() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.w("NotificationHelper", "POST_NOTIFICATIONS permission not granted. Cannot show notification.")
            return
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val bigTextStyle = NotificationCompat.BigTextStyle()
            .bigText("New apps are available!")
            .setBigContentTitle("New Apps")

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New Apps")
            .setContentText("New apps are available!")
            .setStyle(bigTextStyle)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        Log.d("NotificationHelper", "New apps notification shown (ID = $NOTIFICATION_ID)")
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}