package com.example.lightweight.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutWithSets(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "workoutId", // 참조하는 FK의 Column
        entityColumn = "parentWorkoutId" // 참조받는 FK의 Column
    )
    val sets: List<WorkoutSetInfo>
)