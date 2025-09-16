package com.example.gymcompanion.util

import java.time.DayOfWeek
import java.time.LocalTime

fun parseTimeOrNull(text: String): LocalTime? {
    // Aceita "18:30" ou "1830"
    val t = text.trim()
    return try {
        when {
            t.matches(Regex("\\d{1,2}:\\d{2}")) -> LocalTime.parse(
                t.padStart(5, '0') // "9:05" -> "09:05"
            )
            t.matches(Regex("\\d{3,4}")) -> {
                val hh = t.dropLast(2).toInt()
                val mm = t.takeLast(2).toInt()
                LocalTime.of(hh, mm)
            }
            else -> null
        }
    } catch (_: Throwable) { null }
}

fun DayOfWeek.shortPtBr(): String = when (this) {
    DayOfWeek.MONDAY -> "SEG"
    DayOfWeek.TUESDAY -> "TER"
    DayOfWeek.WEDNESDAY -> "QUA"
    DayOfWeek.THURSDAY -> "QUI"
    DayOfWeek.FRIDAY -> "SEX"
    DayOfWeek.SATURDAY -> "SAB"
    DayOfWeek.SUNDAY -> "DOM"
}

fun formatDaysShort(days: Set<DayOfWeek>): String =
    DayOfWeek.values()
        .filter { it in days }
        .joinToString(",") { it.shortPtBr() }

fun formatTimeHHMM(t: LocalTime): String =
    "%02d:%02d".format(t.hour, t.minute)