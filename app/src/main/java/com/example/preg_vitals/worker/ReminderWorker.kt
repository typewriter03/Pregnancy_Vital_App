package com.example.preg_vitals.worker

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.preg_vitals.MainActivity
import com.example.preg_vitals.R      // ensure you have a small icon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReminderWorker(
    private val ctx: Context,
    params: WorkerParameters
) : CoroutineWorker(ctx, params) {

    companion object {
        private const val TAG = "ReminderWorker"
        private const val CHANNEL_ID = "vitals_reminder"
        private const val NOTIF_ID   = 1
    }

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        Log.d(TAG, "doWork started")
        createNotificationChannel()

        // Intent to reopen the app
        val intent = Intent(ctx, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            ctx, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        Log.d(TAG, "Building notification")
        val notification = NotificationCompat.Builder(ctx, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)          // supply any 24×24dp asset
            .setContentTitle("Time to log your vitals!")
            .setContentText("Stay on top of your health. Please update your vitals now!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        Log.d(TAG, "Attempting to show notification with ID: $NOTIF_ID")
        NotificationManagerCompat.from(ctx).notify(NOTIF_ID, notification)
        Log.d(TAG, "Notification posted.")
        Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "Creating notification channel: $CHANNEL_ID")
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Vitals Reminder",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminds the user every 5 hours to log pregnancy vitals"
            }
            ctx.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
            Log.d(TAG, "Notification channel created.")
        } else {
            Log.d(TAG, "Notification channel not needed for this API level.")
        }
    }
}
