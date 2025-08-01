package com.example.preg_vitals.data.repository

import com.example.preg_vitals.data.local.VitalsDao
import com.example.preg_vitals.data.local.VitalsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Abstracts access to the data source.
 */
class VitalsRepository(private val dao: VitalsDao) {

    fun getAllVitals(): Flow<List<VitalsEntity>> = dao.getAllVitals()

    suspend fun insertVitals(vitals: VitalsEntity) = dao.insertVitals(vitals)
    suspend fun delete(v: VitalsEntity) = dao.delete(v)

}
