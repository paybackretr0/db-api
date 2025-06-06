package com.example.newsapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ApiConfig{
    private const val BASE_URL = "https://newsapi.org/"
    val apiService: ApiService by lazy {
        // Tambahkan logging interceptor untuk debugging
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // Buat OkHttpClient dengan interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Konfigurasi Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}
