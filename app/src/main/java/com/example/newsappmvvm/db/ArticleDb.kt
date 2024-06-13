package com.example.newsappmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsappmvvm.models.Article

// Defines the database configuration and serves as the app's main access point to the persisted data.
@Database(
    entities = [Article::class], // Specifies the entities (tables) in the database.
    version = 1 // The version of the database. Increment this number when making schema changes.
)
@TypeConverters(Converters::class) // Specifies type converters for handling custom data types.
abstract class ArticleDb : RoomDatabase() {

    // Abstract function to get the DAO for accessing the Article table.
    abstract fun getArticleDao(): ArticleDao

    companion object {
        // Volatile variable to ensure the value of instance is always up-to-date and visible to all threads.
        @Volatile
        private var instance: ArticleDb? = null
        private val LOCK = Any() // Lock object for synchronizing instance creation.

        // Operator function to create or return the singleton instance of the database.
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        // Private function to create the database instance.
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDb::class.java, // The database class.
                "article_db.db" // The name of the database file.
            ).build()
    }
}