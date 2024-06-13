package com.example.newsappmvvm.db

import androidx.room.TypeConverter
import com.example.newsappmvvm.models.Source

// Class for type conversion between custom types and primitive types for Room database
class Converters {

    // Converts a Source object to a String to store in the database
    @TypeConverter
    fun fromSource(source: Source): String? {
        return source.name
    }

    // Converts a String from the database back to a Source object
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}