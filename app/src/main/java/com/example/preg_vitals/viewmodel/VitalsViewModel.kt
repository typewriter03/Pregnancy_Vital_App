package com.example.preg_vitals.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preg_vitals.data.local.VitalsEntity
import com.example.preg_vitals.data.repository.VitalsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalsViewModel(private val repository: VitalsRepository) : ViewModel() {

    // Real-time list of vitals for the UI.
    val vitals: StateFlow<List<VitalsEntity>> =
        repository.getAllVitals().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addVitals(v: VitalsEntity) = viewModelScope.launch {
        repository.insertVitals(v)
    }
    fun remove(v: VitalsEntity) = viewModelScope.launch { repository.delete(v) }

}
