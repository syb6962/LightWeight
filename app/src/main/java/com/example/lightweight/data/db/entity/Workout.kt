package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var workoutId: Long = 0,
    var title: String = "",
    var memo: String = "",
)
