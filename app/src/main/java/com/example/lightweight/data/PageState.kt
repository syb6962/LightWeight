package com.example.lightweight.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PageState(private val name: String) : Parcelable {
    fun getPageName() = name

    @Parcelize class DailyLog : PageState("FromDailyLog")
    @Parcelize class AddRoutine : PageState("FromAddRoutine")
    @Parcelize class WorkoutList : PageState("FromWorkoutList")
    @Parcelize class Detail : PageState("FromWriteDetail")
}