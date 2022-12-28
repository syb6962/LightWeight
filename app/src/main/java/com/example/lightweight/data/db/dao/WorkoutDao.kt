package com.example.lightweight.data.db.dao

import androidx.room.*
import com.example.lightweight.data.db.entity.DailyWorkout
import com.example.lightweight.data.db.entity.Workout
import com.example.lightweight.data.db.entity.WorkoutList
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

//    @Query("SELECT * FROM WorkoutList")
//    fun getWorkoutList() : WorkoutList

//    @Insert
//    fun insertWorkoutList(workoutList: WorkoutList)

    @Insert
    fun insertWorkout(workout: Workout) : Long

    @Insert
    fun insertSetInfoList(list: List<WorkoutSetInfo>)

    @Query("SELECT * From WorkoutList")
    fun getWorkoutList() : List<WorkoutList>

    @Insert
    suspend fun insertDailyLog(dailyWorkout: DailyWorkout) : Long

//    @Query("SELECT * FROM Workout")
//    @Transaction
//    fun getWorkoutSetInfoList() : LiveData<List<WorkoutWithSets>> // TODO: 함수이름 변경
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertWorkout(workout: Workout) : Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSetInfoList(list: List<WorkoutSetInfo>)
//
//    @Insert
//    fun insertAll(list: List<Workout>)
//
//    @Delete
//    fun delete(detail: Workout)
//
//    @Update
//    fun update(list: List<Workout>)
}