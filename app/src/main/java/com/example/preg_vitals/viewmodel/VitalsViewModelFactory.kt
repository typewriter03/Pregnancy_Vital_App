package com.example.preg_vitals.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.preg_vitals.data.local.VitalsDatabase
import com.example.preg_vitals.data.repository.VitalsRepository

class VitalsViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = VitalsDatabase.getInstance(app).vitalsDao()
        val repo = VitalsRepository(dao)
        @Suppress("UNCHECKED_CAST")
        return VitalsViewModel(repo) as T
    }
}
