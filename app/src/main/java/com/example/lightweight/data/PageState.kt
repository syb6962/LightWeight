package com.example.lightweight.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class PageState(private val e: String) : Parcelable {

    fun getStateName() : String {
        return e
    }
    
    //TODO: 클래스명 새로 짓기. 새로만들기, 수정 등등으로

    @Parcelize class DailyLog(val name: String) : PageState(name)
    @Parcelize class AddRoutine : PageState("")
    @Parcelize class WorkoutList : PageState("")
    @Parcelize class Detail(val name: String) : PageState(name)

    companion object {
        lateinit var curPageState: PageState
    }
    
}