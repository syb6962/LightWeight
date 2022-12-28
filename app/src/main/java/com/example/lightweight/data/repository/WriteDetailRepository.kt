package com.example.lightweight.data.repository

import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.Workout
import com.example.lightweight.data.db.entity.WorkoutSetInfo

class WriteDetailRepository(val dao: WorkoutDao) {
    private var setInfoList = arrayListOf(WorkoutSetInfo(set = 1))
    private var updatedList = listOf<WorkoutSetInfo>()
                get() = setInfoList.toList()

    fun add() {
        val item = WorkoutSetInfo(set = setInfoList.size + 1)
        setInfoList.add(item)
        updatedList = setInfoList.toList()
    }

    fun delete() {
        if(setInfoList.size != 1) {
            setInfoList.let { list ->
                list.removeLast()
                updatedList = list.toList()
            }
        }
    }
    fun getList() : List<WorkoutSetInfo> = updatedList

    fun clearList() {
        setInfoList.clear()
        setInfoList.add(WorkoutSetInfo(set = 1))
    }
}