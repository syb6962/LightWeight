package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bodypart")
data class BodyPart(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val part: String
)