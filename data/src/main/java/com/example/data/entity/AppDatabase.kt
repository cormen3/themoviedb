package com.example.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entity.model.local.ConfigurationEntity

@Database(
    entities = [
        ConfigurationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ConfigurationDao
}
