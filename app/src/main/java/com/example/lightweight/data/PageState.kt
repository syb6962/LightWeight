package com.example.lightweight.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PageState(private val e: String) : Parcelable {
    fun getState() = e

    @Parcelize class startWorkout(val name: String) : PageState(name)
    @Parcelize class editWorkout : PageState("")
    @Parcelize class addWorkout: PageState("addWorkout")

    companion object {
        lateinit var curPageState: PageState
    }
}