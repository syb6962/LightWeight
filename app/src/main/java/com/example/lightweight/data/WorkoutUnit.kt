package com.example.lightweight.data

import androidx.annotation.StringRes
import com.example.lightweight.R

enum class WorkoutUnit(@StringRes val resId: Int) {
    kg(R.string.unit_kg),
    lbs(R.string.unit_lbs);
}