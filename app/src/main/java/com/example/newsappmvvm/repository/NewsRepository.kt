package com.example.newsappmvvm.repository

import com.example.newsappmvvm.api.RetrofitInstance
import com.example.newsappmvvm.db.ArticleDb
import com.example.newsappmvvm.models.Article

class NewsRepository(
    val db: ArticleDb
) {
    suspend fun getBreakingNews(countrycode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countrycode, pageNumber)

    suspend fun searchNews(searchQueary: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQueary, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}