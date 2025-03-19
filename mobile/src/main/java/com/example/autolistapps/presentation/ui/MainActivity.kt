package com.example.autolistapps.presentation.ui

import MainScreenViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.presentation.NotificationHelper
import com.example.autolistapps.presentation.viewmodel.MainScreenViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = AppRepository()
        val factory = MainScreenViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[MainScreenViewModel::class.java]
        viewModel.getListApps()

        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val state = viewModel.appList.collectAsState()
                val items = when (val appListUiState = state.value) {
                    MainScreenViewModel.AppListUiState.Loading -> emptyList()
                    is MainScreenViewModel.AppListUiState.Success -> appListUiState.data
                    is MainScreenViewModel.AppListUiState.Error -> emptyList()
                }
                AppNavigation(apps = items, onPermissionGranted = {
                    Log.d("MainActivity", "Permission granted")
                })
            }
        }
    }
}