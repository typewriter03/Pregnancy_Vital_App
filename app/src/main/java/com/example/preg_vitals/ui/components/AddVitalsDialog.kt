package com.example.preg_vitals.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.preg_vitals.data.local.VitalsEntity

@Composable
fun AddVitalsDialog(
    onDismiss: () -> Unit,
    onSubmit: (VitalsEntity) -> Unit
) {
    // local text-field states
    var systolic by remember { mutableStateOf("") }
    var diastolic by remember { mutableStateOf("") }
    var heart by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var kicks by remember { mutableStateOf("") }

    val isValid = systolic.isNotBlank() &&
              diastolic.isNotBlank() &&
              heart.isNotBlank() &&
              weight.isNotBlank() &&
              kicks.isNotBlank()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Vitals") },
        text = {
            Column {
                OutlinedTextField(systolic, { systolic = it }, label = { Text("Systolic") })
                OutlinedTextField(diastolic, { diastolic = it }, label = { Text("Diastolic") })
                OutlinedTextField(heart, { heart = it }, label = { Text("Heart Rate") })
                OutlinedTextField(weight, { weight = it }, label = { Text("Weight (kg)") })
                OutlinedTextField(kicks, { kicks = it }, label = { Text("Baby Kicks") })
            }
        },
        confirmButton = {
            Button(
                enabled = isValid,
                onClick = {
                    onSubmit(
                        VitalsEntity(
                            systolic = systolic.toIntOrNull() ?: 0,
                            diastolic = diastolic.toIntOrNull() ?: 0,
                            heartRate = heart.toIntOrNull() ?: 0,
                            weight = weight.toFloatOrNull() ?: 0f,
                            babyKicks = kicks.toIntOrNull() ?: 0
                        )
                    )
                }
            ) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
        
    )
}
