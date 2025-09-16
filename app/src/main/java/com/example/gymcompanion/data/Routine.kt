package com.example.gymcompanion.data

import java.time.DayOfWeek
import java.time.LocalTime

data class Routine(
    val id: Long,
    val name: String,
    val days: Set<DayOfWeek>,   // ex: {MONDAY, WEDNESDAY, FRIDAY}
    val time: LocalTime         // ex: 18:30
)
