package com.example.newsappmvvm.repository

import com.example.newsappmvvm.api.RetrofitInstance
import com.example.newsappmvvm.db.ArticleDb

class NewsRepository(
    val db: ArticleDb
) {
    suspend fun getBreakingNews(countrycode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countrycode, pageNumber)
}