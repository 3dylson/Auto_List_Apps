package com.example.autolistapps.presentation.ui.appslist

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.example.autolistapps.data.model.sample.randomItem1
import com.example.autolistapps.data.model.sample.randomItem2
import com.example.autolistapps.domain.toDomainModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenKtTest {

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule(order = 2)
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.POST_NOTIFICATIONS)

    private lateinit var testNavController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            val appList = listOf(randomItem1.toDomainModel(), randomItem2.toDomainModel())
            testNavController = TestNavHostController(LocalContext.current)
            MainScreen(
                isRefreshing = false, onRefresh = {}, uiState = AppListUiState.Success(
                    emptyList()
                ), apps = appList, navController = testNavController
            )
        }
    }

    @Test
    fun firstItem_exists() {
        composeTestRule.onNodeWithText(randomItem1.name!!).assertExists()
    }
}