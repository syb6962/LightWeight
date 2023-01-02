package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity( //TODO: foreignKeys의 Annotation 있으나 마나한것같다..(차이가 없음)
    foreignKeys = [
        ForeignKey(
            entity = DailyWorkout::class,
            parentColumns = arrayOf("dailyId"), // FK로 가져오는 Column의 이름
            childColumns = arrayOf("parentDailyId"), // FK로 받을 Column의 이름
            onDelete = ForeignKey.CASCADE // 부모가 삭제될때 자식도 전부삭제 옵션
        )
    ]
)
data class Workout(
    @PrimaryKey(autoGenerate = true)
    var workoutId: Long = 0,
    var title: String = "",
    var memo: String = "",
    val parentDailyId: Long = 0
)
