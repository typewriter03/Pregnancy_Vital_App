package com.example.preg_vitals.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * One row in the "vitals" table.
 */
@Entity(tableName = "vitals")
data class VitalsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int,
    val weight: Float,
    val babyKicks: Int
)
