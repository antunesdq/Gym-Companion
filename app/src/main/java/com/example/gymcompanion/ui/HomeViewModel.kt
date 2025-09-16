package com.example.gymcompanion.ui // Changed package

import androidx.lifecycle.ViewModel
import com.example.gymcompanion.data.Routine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.concurrent.atomic.AtomicLong

class HomeViewModel : ViewModel() {
    private val _routines = MutableStateFlow<List<Routine>>(emptyList())
    val routines: StateFlow<List<Routine>> = _routines

    private val idGen = AtomicLong(1)

    fun addRoutine(name: String, days: Set<DayOfWeek>, time: LocalTime) {
        val newOne = Routine(
            id = idGen.getAndIncrement(),
            name = name.trim(),
            days = days,
            time = time
        )
        _routines.value = _routines.value + newOne
    }
}