package com.example.lightweight.domain

import androidx.annotation.ArrayRes
import com.example.lightweight.R

enum class BodyType(_resourceId: Int) {
    CHEST(R.array.chest_workout_list),
    BACK(R.array.back_workout_list),
    LEG(R.array.leg_workout_list),
    SHOULDER(R.array.shoulder_workout_list),
    BICEPS(R.array.biceps_workout_list),
    TRICEPS(R.array.triceps_workout_list),
    ABS(R.array.abs_workout_list);

    @ArrayRes
    private val resourceId: Int = _resourceId

    fun getResourceId() : Int = resourceId
}