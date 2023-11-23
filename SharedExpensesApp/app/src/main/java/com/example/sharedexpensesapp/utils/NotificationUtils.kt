
package com.example.sharedexpensesapp.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sharedexpensesapp.R

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Default"
        val descriptionText = ""
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("default_id", name, importance).apply {
            description = descriptionText
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}

fun sendNotification(context: Context) {
    createNotificationChannel(context)

    val builder = NotificationCompat.Builder(context, "default_id")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("My Notification")
        .setContentText("Notification")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(1, builder.build())
    }
}
