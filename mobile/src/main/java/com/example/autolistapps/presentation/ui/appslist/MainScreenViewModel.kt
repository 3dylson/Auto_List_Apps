package com.example.autolistapps.presentation.ui.appslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.domain.CheckNewAppsUseCase
import com.example.autolistapps.domain.model.AppItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: AppRepository,
    private val checkNewAppsUseCase: CheckNewAppsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AppListUiState>(AppListUiState.Loading)
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            checkNewAppsUseCase.invoke(Unit)

            repository.appList.collect { list ->
                _uiState.value = if (list.isEmpty()) {
                    AppListUiState.Loading
                } else {
                    AppListUiState.Success(list)
                }
            }
        }
    }


    fun onRefresh() {
        viewModelScope.launch {
            Log.d("MainScreenViewModel", "onRefresh called")
            _isRefreshing.value = true
            try {
                checkNewAppsUseCase.invoke(Unit)
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun getAppById(appId: Int): StateFlow<AppItem?> {
        return repository.getAppById(appId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }
}

sealed class AppListUiState {
    data object Loading : AppListUiState()
    data class Success(val data: List<AppItem>) : AppListUiState()
    data class Error(val exception: Throwable?) : AppListUiState()
}