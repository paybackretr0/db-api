package com.example.newsapp.ui.news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.DataStorePref
import com.example.newsapp.data.api.ApiConfig
import com.example.newsapp.data.response.ArticlesItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class UiState(
    val isLoading: Boolean = false,
    val articles: List<ArticlesItem> = emptyList(), // Ubah ke non-nullable untuk efisiensi
    val error: String? = null
)

class NewsViewModel(context: Context) : ViewModel() {
    private val dataStoreUtil = DataStorePref(context)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // Mendapatkan status login
    val isLoggedIn = dataStoreUtil.isLoggedIn

    // Simulasi login
    fun login() {
        viewModelScope.launch {
            dataStoreUtil.saveLoginStatus(true)
        }
    }

    // Simulasi logout
    fun logout() {
        viewModelScope.launch {
            dataStoreUtil.saveLoginStatus(false)
        }
    }

    // Memanggil API NewsAPI
    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            try {
                val response = ApiConfig.apiService.getTopHeadlines("us", apiKey)
                if (response.isSuccessful) {
                    val articles = response.body()?.articles?.filterNotNull() ?: emptyList()
                    _uiState.value = UiState(isLoading = false, articles = articles)
                } else {
                    _uiState.value = UiState(
                        isLoading = false,
                        error = "Error: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = UiState(
                    isLoading = false,
                    error = "Exception: ${e.message}"
                )
            }
        }
    }

    // ViewModel Factory untuk menyediakan Context
    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}