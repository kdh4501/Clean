package com.dhkim.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dhkim.data.dao.TextDao
import com.dhkim.data.model.TextEntity

@Database(
    entities = [TextEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun textDao(): TextDao
}