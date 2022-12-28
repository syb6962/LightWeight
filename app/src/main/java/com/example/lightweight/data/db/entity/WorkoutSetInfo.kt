package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lightweight.data.WorkoutUnit

@Entity( //TODO: foreignKeys의 Annotation 있으나 마나한것같다..(차이가 없음)
    foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = arrayOf("workoutId"), // FK로 가져오는 Column의 이름
            childColumns = arrayOf("parentWorkoutId"), // FK로 받을 Column의 이름
            onDelete = ForeignKey.CASCADE // 부모가 삭제될때 자식도 전부삭제 옵션
        )
    ]
)
data class WorkoutSetInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val set: Int,
    var weight: String = "0",
    var reps: String = "0",
    var unit: WorkoutUnit = WorkoutUnit.kg,
    val parentWorkoutId: Long = 0
)