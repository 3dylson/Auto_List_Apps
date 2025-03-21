package com.example.autolistapps.data

import com.example.autolistapps.data.local.AppItemLocalModel
import com.example.autolistapps.data.local.AppItemLocalModelDao
import com.example.autolistapps.data.model.All
import com.example.autolistapps.data.model.ApiResponse
import com.example.autolistapps.data.model.Data
import com.example.autolistapps.data.model.Datasets
import com.example.autolistapps.data.model.ListApps
import com.example.autolistapps.data.model.Responses
import com.example.autolistapps.data.model.sample.randomItem1
import com.example.autolistapps.data.model.sample.randomItem2
import com.example.autolistapps.data.remote.AptoideAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppRepositoryImplTest {

    @Test
    fun appItemLocalModel_insertAll() = runTest {
        val repository = AppRepositoryImpl(TestAppItemLocalModelDao(), TestAptoideAPI())
        repository.refresh()
        val appList = repository.appList.first()
        assert(appList.size == 2)
    }

    @Test
    fun appItemLocalModel_getById() = runTest {
        val repository = AppRepositoryImpl(TestAppItemLocalModelDao(), TestAptoideAPI())
        repository.refresh()
        val app = repository.getAppById(101).first()
        assert(app?.id == 101)
    }
}

private class TestAppItemLocalModelDao : AppItemLocalModelDao {

    private val data = mutableListOf<AppItemLocalModel>()


    override fun getAll(): Flow<List<AppItemLocalModel>> = flow {
        emit(data)
    }

    override fun getById(id: Int): Flow<AppItemLocalModel?> = flow {
        emit(data.find { it.id == id })
    }

    override suspend fun insertAll(appList: List<AppItemLocalModel>) {
        data.addAll(appList)
    }

    override suspend fun getAllOnce(): List<AppItemLocalModel> = data

}


private class TestAptoideAPI : AptoideAPI {
    override suspend fun getListApps(): ApiResponse {
        return ApiResponse(
            Responses(
                ListApps(
                    Datasets(
                        All(
                            Data(
                                hidden = null,
                                limit = null,
                                list = listOf(
                                    randomItem1,
                                    randomItem2
                                ),
                                next = null,
                                offset = null,
                                total = null
                            ),
                            null
                        )
                    ),
                    null
                )
            ),
            null
        )
    }

}