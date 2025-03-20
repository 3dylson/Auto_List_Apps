package com.example.autolistapps.presentation.ui.appslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.domain.model.AppItem
import com.example.autolistapps.presentation.ui.appslist.AppListUiState.Error
import com.example.autolistapps.presentation.ui.appslist.AppListUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: AppRepository) :
    ViewModel() {
        val uiState: StateFlow<AppListUiState> = repository
        .appList.map<List<AppItem>, AppListUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppListUiState.Loading)

    fun getAppById(appId: Int): StateFlow<AppItem?> {
        return repository.getAppById(appId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    }
}

sealed class AppListUiState {
    data object Loading : AppListUiState()
    data class Success(val data: List<AppItem>) : AppListUiState()
    data class Error(val exception: Throwable) : AppListUiState()
}