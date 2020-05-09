package com.example.data.entity.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "configuration")
data class ConfigurationEntity(
    @PrimaryKey
    val id: Int?,
    val baseUrl: String?
)