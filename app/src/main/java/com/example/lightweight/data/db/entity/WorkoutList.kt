package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutList(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workout: String,
    val bodyPartId: Long
)