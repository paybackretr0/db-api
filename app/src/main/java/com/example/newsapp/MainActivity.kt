package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.auth.LoginScreen
import com.example.newsapp.ui.news.NewsAppScreen
import com.example.newsapp.ui.news.NewsViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var isLoggedIn by remember { mutableStateOf(false) }

            // Buat ViewModel dengan factory
            val viewModel: NewsViewModel = viewModel(
                factory = NewsViewModel.Factory(this@MainActivity)
            )

            // Amati status login dari DataStore
            LaunchedEffect(Unit) {
                viewModel.isLoggedIn.collectLatest { loggedIn ->
                    isLoggedIn = loggedIn
                }
            }

            // Setup navigasi
            NavHost(
                navController = navController,
                startDestination = if (isLoggedIn) "home" else "login"
            ) {
                composable("login") {
                    LoginScreen(
                        viewModel = viewModel,
                        onLoginSuccess = { navController.navigate("home") }
                    )
                }
                composable("home") {
                    NewsAppScreen(
                        viewModel = viewModel,
                        onLogout = { navController.navigate("login") }
                    )
                }
            }
        }
    }
}