package com.example.autolistapps.presentation.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.rule.GrantPermissionRule
import com.example.autolistapps.data.model.sample.randomItem1
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AppNavigationKtTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.POST_NOTIFICATIONS)

    @Test
    fun navigateToDetailAndCheckDownloadDialog() {
        val appName = "App Store"

        composeTestRule.onNodeWithText(randomItem1.name!!, substring = true).assertExists().performClick()

        composeTestRule.onNodeWithText("Store:", substring = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Downloads:", substring = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Download")
            .performClick()

        composeTestRule.onNodeWithText("Download is not available in demo mode")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("OK")
            .performClick()

        composeTestRule.onNodeWithText("Download is not available in demo mode")
            .assertDoesNotExist()

        composeTestRule.onNodeWithContentDescription("Go back")
            .performClick()


        composeTestRule.onNodeWithText(appName, substring = true)
            .assertIsDisplayed()
    }

}