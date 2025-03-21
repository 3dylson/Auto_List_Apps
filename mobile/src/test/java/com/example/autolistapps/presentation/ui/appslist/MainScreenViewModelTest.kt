package com.example.autolistapps.presentation.ui.appslist

import com.example.autolistapps.data.AppRepository
import com.example.autolistapps.domain.CheckNewAppsUseCase
import com.example.autolistapps.domain.model.AppItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainScreenViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = MainScreenViewModel(
            TestAppRepositoryImpl(),
            CheckNewAppsUseCase(TestAppRepositoryImpl(), testDispatcher)
        )
        assertEquals(viewModel.uiState.first(), AppListUiState.Loading)
    }

}

private class TestAppRepositoryImpl : AppRepository {

    private val data = mutableListOf<AppItem>()

    override val appList: Flow<List<AppItem>>
        get() = flow { emit(data.toList()) }

    override suspend fun refresh(): Result<List<AppItem>> {
        return Result.success(data)
    }

    override fun getAppById(id: Int): Flow<AppItem?> {
        throw NotImplementedError()
    }

    override suspend fun hasNewApps(): Boolean {
        return true
    }


}