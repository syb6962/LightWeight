package com.example.lightweight.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DailyWithWorkouts(
    @Embedded val dailyWorkout: DailyWorkout,
    @Relation(
        parentColumn = "dailyId", // 참조하는 FK의 Column
        entityColumn = "parentDailyId" // 참조받는 FK의 Column
    )
    val workouts: List<Workout>
)
