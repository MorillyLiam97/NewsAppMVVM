package com.example.newsappmvvm.api

import com.example.newsappmvvm.models.NewsResponse
import com.example.newsappmvvm.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Interface for defining API endpoints related to news
interface NewsApi {

    // Endpoint for fetching the top headlines
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "za", // Default country code set to "za" (South Africa)
        @Query("page")
        pgNumber: Int = 1, // Default page number set to 1
        @Query("apiKey")
        apiKey: String = API_KEY // API key for authentication
    ): Response<NewsResponse>

    // Endpoint for searching news articles based on a query
    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String, // Search query provided by the user
        @Query("page")
        pgNumber: Int = 1, // Default page number set to 1
        @Query("apiKey")
        apiKey: String = API_KEY // API key for authentication
    ): Response<NewsResponse>
}