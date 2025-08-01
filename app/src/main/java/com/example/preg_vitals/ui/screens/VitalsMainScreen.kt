package com.example.preg_vitals.ui.screens

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.preg_vitals.data.local.VitalsEntity
import com.example.preg_vitals.ui.components.AddVitalsDialog
import com.example.preg_vitals.ui.components.VitalsListItem
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VitalsMainScreen(
    vitals: List<VitalsEntity>,
    onAddVitals: (VitalsEntity) -> Unit,
    onDelete: (VitalsEntity) -> Unit,
    onToggleTheme: (Boolean) -> Unit,
    onSetReminder: (Int, Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var darkMode by remember { mutableStateOf(false) }   // UI-only switch

    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Track My Pregnancy") },
                actions = {
                    IconButton(onClick = {
                        darkMode = !darkMode
                        onToggleTheme(darkMode)
                    }) {
                        Icon(
                            if (darkMode) Icons.Filled.DarkMode
                            else Icons.Filled.LightMode,
                            contentDescription = "Toggle theme"
                        )
                    }
                    IconButton(onClick = {
                        val now = Calendar.getInstance()
                        TimePickerDialog(
                            ctx,
                            { _, h, m -> onSetReminder(h, m) },
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            false
                        ).show()
                    }) {
                        Text("⏰")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Vitals")
            }
        }
    ) { padding ->
        if (vitals.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No vitals yet — tap + to add")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(vitals, key = { it.id }) { item ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = { state ->
                            if (state == DismissValue.DismissedToStart) {
                                onDelete(item)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        background = {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.Red)
                                    .padding(16.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text("Delete", color = Color.White)
                            }
                        },
                        dismissContent = { VitalsListItem(item) }
                    )
                }
            }
        }

        if (showDialog) {
            AddVitalsDialog(
                onDismiss = { showDialog = false },
                onSubmit = {
                    onAddVitals(it)
                    showDialog = false
                }
            )
        }
    }
}
