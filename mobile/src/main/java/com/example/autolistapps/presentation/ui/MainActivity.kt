package com.example.autolistapps.presentation.ui

import MainScreenViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.presentation.viewmodel.MainScreenViewModelFactory
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = AppRepository()

        // Create the custom factory
        val factory = MainScreenViewModelFactory(repository)

        // Use the custom factory with ViewModelProvider
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
                AppNavigation(apps = items)
            }
        }
    }
}