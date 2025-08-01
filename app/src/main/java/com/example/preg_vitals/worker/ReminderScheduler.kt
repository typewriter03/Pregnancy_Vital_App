package com.example.preg_vitals.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

object ReminderScheduler {
    private const val TAG = "ReminderScheduler"

    fun schedule(ctx: Context, hour: Int, min: Int) {
        Log.d(TAG, "Attempting to schedule reminder for $hour:$min")

        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DATE, 1) // tomorrow
                Log.d(TAG, "Time is in the past, scheduling for tomorrow.")
            }
        }
        val delay = cal.timeInMillis - System.currentTimeMillis()
        Log.d(TAG, "Calculated initial delay: $delay ms")

        if (delay < 0) {
            Log.e(TAG, "Error: Calculated delay is negative. Notification might not fire as expected.")
            // Optionally, handle this case, e.g., by adding 24 hours if the time was meant for the next day
            // but the logic above didn't catch it (shouldn't happen with current logic).
        }

        val req = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .addTag("custom_reminder")
            .build()

        WorkManager.getInstance(ctx).cancelAllWorkByTag("custom_reminder") // Cancel previous reminders
        WorkManager.getInstance(ctx).enqueue(req)
        Log.d(TAG, "Enqueued OneTimeWorkRequest for ReminderWorker.")
    }
}
