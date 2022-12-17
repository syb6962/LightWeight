package com.example.lightweight

import android.app.Application
import com.example.lightweight.data.db.WorkoutDatabase
import com.example.lightweight.data.repository.WorkoutListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WorkoutApplication : Application() {
    val database by lazy { WorkoutDatabase.getDatabase(this) }
    val workoutListRepo by lazy { WorkoutListRepository(database.workoutDao()) }

//    val detailRepo: DetailRepository by lazy { DetailRepository(database.workoutDao()) }
//    val routineRepo: RoutineRepository by lazy { RoutineRepository(database.workoutDao()) }
}