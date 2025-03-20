package com.example.autolistapps.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autolistapps.presentation.ui.appslist.DetailScreen
import com.example.autolistapps.presentation.ui.appslist.MainScreen

@Composable
fun AppNavigation(onPermissionGranted: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                viewModel = hiltViewModel(),
                navController = navController,
                onPermissionGranted = onPermissionGranted
            )
        }
        composable(Screen.DetailScreen.route + "/{appId}") { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull()
            if (appId != null) {
                DetailScreen(appId = appId, navController = navController)
            }
        }
    }
}