package com.example.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsappmvvm.models.Article

// Data Access Object (DAO) for performing database operations on Article entities
@Dao
interface ArticleDao {

    // Inserts an article into the database.
    // If there is a conflict (e.g., an article with the same primary key), it replaces the existing article.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    // Retrieves all articles from the database as LiveData, which allows observing the data for changes.
    @Query("SELECT * FROM Articles")
    fun getAllArticles(): LiveData<List<Article>>

    // Deletes a specific article from the database.
    @Delete
    suspend fun deleteArticle(article: Article)
}