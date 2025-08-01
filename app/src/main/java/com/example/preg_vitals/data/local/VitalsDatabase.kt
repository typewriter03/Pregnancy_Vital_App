package com.example.preg_vitals.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database singleton.
 */
@Database(entities = [VitalsEntity::class], version = 1, exportSchema = false)
abstract class VitalsDatabase : RoomDatabase() {

    abstract fun vitalsDao(): VitalsDao

    companion object {
        @Volatile
        private var INSTANCE: VitalsDatabase? = null

        fun getInstance(context: Context): VitalsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    VitalsDatabase::class.java,
                    "vitals_db"
                ).build().also { INSTANCE = it }
            }
    }
}
