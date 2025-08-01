package com.example.preg_vitals.ui.screens

import android.app.TimePickerDialog
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun ReminderScreen(onTimeChosen: (Int, Int) -> Unit) {
    val ctx = LocalContext.current
    var timeLabel by remember { mutableStateOf("Not set") }

    Button(onClick = {
        val now = Calendar.getInstance()
        TimePickerDialog(
            ctx,
            { _, h, m ->
                timeLabel = String.format("%02d:%02d", h, m)
                onTimeChosen(h, m)
            },
            now.get(Calendar.HOUR_OF_DAY),
            now.get(Calendar.MINUTE),
            false
        ).show()
    }) { Text("Choose daily reminder time ($timeLabel)") }
}
