package com.example.lightweight.data.db

import androidx.room.TypeConverter
import com.example.lightweight.data.WorkoutUnit

/*****************************************
 * kg, lbs 단위 변경을 위한 DB TypeConverter *
 *****************************************/
class UnitConverter {
    @TypeConverter // 가져올때
    fun fromWorkoutUnit(unit: WorkoutUnit) : String {
        return unit.name
    }

    @TypeConverter // 저장할때
    fun toWorkoutUnit(name: String) : WorkoutUnit {
        return enumValueOf(name)
    }
}