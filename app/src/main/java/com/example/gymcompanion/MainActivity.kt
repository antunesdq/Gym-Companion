package com.example.gymcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gymcompanion.ui.AppNav
import com.example.gymcompanion.ui.HomeViewModel
import com.example.gymcompanion.ui.theme.GymCompanionTheme
import com.example.gymcompanion.util.formatDaysShort
import com.example.gymcompanion.util.formatTimeHHMM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymCompanionTheme {
                AppNav()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController, vm: HomeViewModel) {
    val routines by vm.routines.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Gym Companion") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { nav.navigate("routine/new") }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        if (routines.isEmpty()) {
            Box(
                Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhuma rotina ainda! Toque no + para criar.")
            }
        } else {
            LazyColumn(Modifier.padding(paddingValues)) {
                items(routines) { r ->
                    ListItem(
                        headlineContent = { Text(r.name) },
                        supportingContent = { Text("${formatDaysShort(r.days)} • ${formatTimeHHMM(r.time)}") },
                        modifier = Modifier
                            .clickable { /* depois: detalhes */ }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CreateRoutineScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Tela de criar rotina")
        Button(onClick = { nav.popBackStack() }) {
            Text("Voltar")
        }
    }
}
