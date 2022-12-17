package com.example.lightweight.data.repository

import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.WorkoutList
import com.example.lightweight.domain.BodyPart

class WorkoutListRepository(private val dao: WorkoutDao) {

   fun getWorkoutList(part: BodyPart): List<String> {
        return when(part) {
            is BodyPart.Chest -> getList(0)
            is BodyPart.Back -> getList(1)
            is BodyPart.Leg -> getList(2)
            is BodyPart.Shoulder -> getList(3)
            is BodyPart.Biceps -> getList(4)
            is BodyPart.Triceps -> getList(5)
            is BodyPart.Abs -> getList(6)
        }
    }

    private fun getList(id: Long) : List<String> {
        val list = dao.getWorkoutList()
        return list.filter { it.bodyPartId == id }.map { it.workout }
    }
}