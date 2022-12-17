package com.example.lightweight.data

import com.example.lightweight.domain.BodyType

interface WorkoutListSource {
    fun getWorkoutListByPart(type: BodyType) : List<String>
}