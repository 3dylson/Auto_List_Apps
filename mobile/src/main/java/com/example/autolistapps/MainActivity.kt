package com.example.autolistapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autolistapps.model.Item0
import com.example.autolistapps.model.sample.sampleItems
import com.example.autolistapps.ui.DetailScreen
import com.example.autolistapps.ui.MainScreen
import com.example.autolistapps.ui.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                AppNavigation(apps = sampleItems)
            }
        }
    }
}

@Composable
fun AppNavigation(apps: List<Item0>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(apps = apps, navController = navController)
        }
        composable(Screen.DetailScreen.route + "/{appId}") { backStackEntry ->
            // Find the app by its ID from the list;
            // TODO: ViewModel
            val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull()
            val app = apps.find { it.id == appId }
            if (app != null) {
                DetailScreen(app = app)
            }
        }
    }
}