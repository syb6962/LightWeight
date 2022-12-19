package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lightweight.domain.BodyPart

@Entity
data class DailyWorkout(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val date: String,
    val bodyPart: String, // bodyPart
)