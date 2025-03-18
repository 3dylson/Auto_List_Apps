import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.domain.model.AppItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainScreenViewModel(private val repository: AppRepository) : ViewModel() {

    sealed class AppListUiState {
        data object Loading : AppListUiState()
        data class Success(val data: List<AppItem>) : AppListUiState()
        data class Error(val exception: Throwable) : AppListUiState()
    }

    private val _appList = MutableStateFlow<AppListUiState>(AppListUiState.Loading)
    val appList: StateFlow<AppListUiState> = _appList.asStateFlow()

    fun getListApps() {
        viewModelScope.launch {
            repository.getListApps()
                .onStart { _appList.value = AppListUiState.Loading }
                .catch { throwable ->
                    _appList.value = AppListUiState.Error(throwable)
                }
                .collect { result ->
                    _appList.value = when {
                        result.isSuccess -> AppListUiState.Success(result.getOrThrow())
                        else -> AppListUiState.Error(result.exceptionOrNull() ?: UnknownError())
                    }
                }
        }
    }
}