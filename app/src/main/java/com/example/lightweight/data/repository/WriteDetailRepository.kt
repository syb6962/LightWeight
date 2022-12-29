package com.example.lightweight.data.repository

import com.example.lightweight.data.WorkoutUnit
import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.Workout
import com.example.lightweight.data.db.entity.WorkoutSetInfo

class WriteDetailRepository(val dao: WorkoutDao) {
    private var setInfoList = arrayListOf(WorkoutSetInfo(set = 1)) // 세트 1개는 고정
    private var updatedList = listOf<WorkoutSetInfo>() //TODO: 질문하기. 그냥 updateList =  setInfoList.toList()와 결과가 다르다.
                get() = setInfoList.toList()

    fun changeUnit(unit: WorkoutUnit) {
        //TODO: 화면이 업데이트 되지않는것을 확인
        updatedList = setInfoList.map { setInfo ->
            setInfo.copy(unit = unit)
        }
    }

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

    //TODO: 필요한지 의문
    fun getList() : List<WorkoutSetInfo> = updatedList

    fun complete(title: String, memo: String) {
        val workout = Workout(title = title, memo = memo)

        // Workout 삽입 및 삽입된 Workout의 ID 반환
        // 반환된 ID를 기반으로 DB에서 Workout과 WorkoutList는 1:N 기반으로 저장됨
        val workoutId = dao.insertWorkout(workout)
        val newWorkoutSetInfoList = setInfoList.map { setInfo -> // workoutId를 기반으로 새 리스트 리턴
            setInfo.copy(parentWorkoutId = workoutId)
        }
        dao.insertSetInfoList(newWorkoutSetInfoList)
    }
}