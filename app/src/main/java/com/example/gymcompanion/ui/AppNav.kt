package com.example.gymcompanion.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymcompanion.ui.HomeViewModel
import com.example.gymcompanion.HomeScreen
// Removed: import com.example.gymcompanion.CreateRoutineScreen 
// Now CreateRoutineScreen(nav, homeVm) will resolve to com.example.gymcompanion.ui.CreateRoutineScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()
    val homeVm: HomeViewModel = viewModel()

    NavHost(navController = nav, startDestination = "home") {
        composable("home") { HomeScreen(nav, homeVm) }
        composable("routine/new") { CreateRoutineScreen(nav, homeVm) }
    }
}
