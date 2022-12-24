package com.example.lightweight.data.repository

import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.WorkoutSetInfo

class WriteDetailRepository(val dao: WorkoutDao) {
    private var setInfoList = ArrayList<WorkoutSetInfo>()
    private lateinit var updatedList: List<WorkoutSetInfo>

    fun add(): List<WorkoutSetInfo> {
        val item = WorkoutSetInfo(set = 1)
        setInfoList.add(item)
        updatedList = setInfoList.toList()

        return updatedList
    }

    fun delete(): List<WorkoutSetInfo> {
        return updatedList
    }
}