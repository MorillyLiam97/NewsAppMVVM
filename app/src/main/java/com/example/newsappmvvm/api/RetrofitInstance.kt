package com.example.newsappmvvm.api

import com.example.newsappmvvm.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Class to manage the Retrofit instance for making network requests
class RetrofitInstance {
    companion object {
        // Lazy initialization of the Retrofit instance
        private val retrofit by lazy {
            // Creating an interceptor for logging HTTP request and response data
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            // Building the OkHttpClient with the logging interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            // Building the Retrofit instance with the base URL and Gson converter
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        // Lazy initialization of the API service using the Retrofit instance
        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}