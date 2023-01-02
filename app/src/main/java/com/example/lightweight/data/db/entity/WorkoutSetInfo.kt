package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lightweight.R
import com.example.lightweight.data.WorkoutUnit

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("workoutId"),
            childColumns = arrayOf("parentWorkoutId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WorkoutSetInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val set: Int,
    var weight: String = "0",
    var reps: String = "0",
    var unit: String = "kg",
    val parentWorkoutId: Long = 0
)