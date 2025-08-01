package com.example.preg_vitals

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.preg_vitals.data.local.Prefs
import com.example.preg_vitals.data.local.VitalsEntity
import com.example.preg_vitals.ui.screens.ReminderScreen
import com.example.preg_vitals.ui.screens.VitalsMainScreen
import com.example.preg_vitals.viewmodel.VitalsViewModel
import com.example.preg_vitals.viewmodel.VitalsViewModelFactory
import com.example.preg_vitals.worker.ReminderScheduler
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = Prefs(this)
        val viewModel = ViewModelProvider(
            this,
            VitalsViewModelFactory(application)
        )[VitalsViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            prefs.reminderTime.first().let { (hour, minute) ->
                ReminderScheduler.schedule(this@MainActivity, hour, minute)
            }
        }

        setContent {
            val vitals by viewModel.vitals.collectAsState()
            val darkMode by prefs.darkFlow.collectAsState(initial = false)

            MaterialTheme(
                colors = if (darkMode) MaterialTheme.colors.copy(
                    primary = MaterialTheme.colors.primary,
                    background = MaterialTheme.colors.background
                ) else MaterialTheme.colors
            ) {
                VitalsMainScreen(
                    vitals = vitals,
                    onAddVitals = { viewModel.addVitals(it) },
                    onDelete = { viewModel.remove(it) },
                    onToggleTheme = { enabled ->
                        lifecycleScope.launch { prefs.setDark(enabled) }
                    },
                    onSetReminder = { h, m ->
                        lifecycleScope.launch { prefs.saveTime(h, m) }
                        ReminderScheduler.schedule(this@MainActivity, h, m)
                        Toast.makeText(
                            this@MainActivity,
                            "Reminder set for %02d:%02d".format(h, m),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
