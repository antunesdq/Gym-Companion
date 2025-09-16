package com.example.gymcompanion.ui

// Removed @file:OptIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi // Explicit import
import androidx.compose.foundation.layout.FlowRow // Specific import
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button // Specific import
import androidx.compose.material3.ExperimentalMaterial3Api // Explicit import
import androidx.compose.material3.FilterChip // Specific import
import androidx.compose.material3.OutlinedTextField // Specific import
import androidx.compose.material3.Text // Specific import
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymcompanion.util.parseTimeOrNull
import java.time.DayOfWeek

@OptIn(ExperimentalMaterial3Api::class) // For OutlinedTextField and Button
@Composable
fun CreateRoutineScreen(nav: NavController, vm: HomeViewModel) {
    var name by remember { mutableStateOf("") }
    var timeText by remember { mutableStateOf("") } // "18:30" ou "1830"
    var selected by remember { mutableStateOf(setOf<DayOfWeek>()) }

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nome da rotina") })
        DaysOfWeekChips(selected = selected, onToggle = { day ->
            selected = if (day in selected) selected - day else selected + day
        })
        OutlinedTextField(
            value = timeText,
            onValueChange = { timeText = it },
            label = { Text("Hora (ex: 18:30 ou 1830)") }
        )
        val time = parseTimeOrNull(timeText)

        Button(
            onClick = {
                vm.addRoutine(name, selected, requireNotNull(time))
                nav.popBackStack()
            },
            enabled = name.isNotBlank() && selected.isNotEmpty() && time != null
        ) {
            Text("Salvar")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class) // For FlowRow and FilterChip
@Composable
private fun DaysOfWeekChips(
    selected: Set<DayOfWeek>,
    onToggle: (DayOfWeek) -> Unit
) {
    val order = listOf(
        DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
    )
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 4
    ) {
        order.forEach { d ->
            FilterChip(
                selected = d in selected,
                onClick = { onToggle(d) },
                label = { Text(short(d)) }
            )
        }
    }
}

@Composable
private fun short(d: DayOfWeek): String = when (d) {
    DayOfWeek.MONDAY -> "SEG"
    DayOfWeek.TUESDAY -> "TER"
    DayOfWeek.WEDNESDAY -> "QUA"
    DayOfWeek.THURSDAY -> "QUI"
    DayOfWeek.FRIDAY -> "SEX"
    DayOfWeek.SATURDAY -> "SAB"
    DayOfWeek.SUNDAY -> "DOM"
}