package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GarouaSchoolApp()
                }
            }
        }
    }
}

@Composable
fun GarouaSchoolApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onNavigateToDashboard = { role ->
                    navController.navigate("dashboard/$role") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("dashboard/{role}") { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "Élève"
            DashboardScreen(
                role = role,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard/$role") { inclusive = true }
                    }
                }
            )
        }
    }
}
