package com.example.autolistapps.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autolistapps.domain.model.AppItem
import com.example.autolistapps.presentation.ui.appslist.DetailScreen
import com.example.autolistapps.presentation.ui.appslist.MainScreen

@Composable
fun AppNavigation(apps: List<AppItem>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(apps = apps, navController = navController)
        }
        composable(Screen.DetailScreen.route + "/{appId}") { backStackEntry ->
            // Find the app by its ID from the list;
            // TODO: ViewModel
            val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull()
            val app = apps.find { it.id == appId }
            if (app != null) {
                DetailScreen(app = app)
            }
        }
    }
}