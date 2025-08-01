package com.example.preg_vitals.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.preg_vitals.data.local.VitalsEntity
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun VitalsListItem(entity: VitalsEntity) {
    val date = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
        .format(Date(entity.timestamp))

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("BP: ${entity.systolic}/${entity.diastolic} mmHg")
                    Text("HR: ${entity.heartRate} bpm")
                }
                Column {
                    Text("Weight: ${entity.weight} kg")
                    Text("Kicks: ${entity.babyKicks}")
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(date, style = MaterialTheme.typography.caption)
        }
    }
}
