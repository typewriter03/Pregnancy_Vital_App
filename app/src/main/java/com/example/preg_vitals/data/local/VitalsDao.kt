package com.example.preg_vitals.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

/**
 * CRUD operations for vitals.
 */
@Dao
interface VitalsDao {

    // Stream of all vitals, newest first.
    @Query("SELECT * FROM vitals ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<VitalsEntity>>

    // Insert or replace a row.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVitals(vitals: VitalsEntity)

    @Delete suspend fun delete(v: VitalsEntity)

}
