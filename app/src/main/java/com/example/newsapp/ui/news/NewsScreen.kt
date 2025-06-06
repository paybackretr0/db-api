package com.example.newsapp.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.response.ArticlesItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewsAppScreen(viewModel: NewsViewModel, onLogout: () -> Unit) {
    // Ganti dengan API Key Anda dari newsapi.org
    val apiKey = "dddffd9ae7e344d7afe7491438066b81"
    val uiState by viewModel.uiState.collectAsState()
    var isLoggedIn by remember { mutableStateOf(false) }

    // Amati status login dari DataStore
    LaunchedEffect(Unit) {
        viewModel.isLoggedIn.collectLatest { loggedIn ->
            isLoggedIn = loggedIn
            if (!loggedIn) onLogout() // Kembali ke login jika logout
        }
    }

    // Panggil API saat login
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            viewModel.fetchNews(apiKey)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Status login
            Text(
                text = if (isLoggedIn) "Logged In" else "Logged Out",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol logout
            Button(
                onClick = {
                    viewModel.logout()
                    onLogout()
                }
            ) {
                Text("Logout")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tampilkan status loading, error, atau daftar berita
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                uiState.articles.isNotEmpty() -> {
                    LazyColumn {
                        items(uiState.articles) { article ->
                            ArticleItem(article)
                        }
                    }
                }
                else -> {
                    Text(text = "No data available. Please login to fetch news.")
                }
            }
        }
    }
}

@Composable
fun ArticleItem(article: ArticlesItem?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = article?.title ?: "No Title",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article?.description ?: "No Description",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Source: ${article?.source?.name ?: "Unknown"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}