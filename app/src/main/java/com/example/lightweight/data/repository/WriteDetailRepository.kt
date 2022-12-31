package com.example.lightweight.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.lightweight.data.WorkoutUnit
import com.example.lightweight.data.db.UnitState
import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.Workout
import com.example.lightweight.data.db.entity.WorkoutSetInfo

class WriteDetailRepository(val dao: WorkoutDao) {
    private var setInfoList = arrayListOf(WorkoutSetInfo(set = 1)) // 세트 1개는 고정
    private var updatedList = listOf<WorkoutSetInfo>() //TODO: 질문하기. 그냥 updateList =  setInfoList.toList()와 결과가 다르다.
                get() = setInfoList.toList()

    @RequiresApi(Build.VERSION_CODES.N)
    fun changeUnit(unit: WorkoutUnit)  {
        // setInfList의 모든 원소를 바꾸는데
        // copy를 사용해서 unit을 바꾸고 각 원소에 새로운 주소값을 할당함
        setInfoList.replaceAll { it ->
            it.copy(unit= unit)
        }
    }

    fun add() {
        val item = WorkoutSetInfo(set = setInfoList.size + 1)
        setInfoList.add(item)
    }

    fun delete() {
        if(setInfoList.size != 1) {
            setInfoList.let { list ->
                list.removeLast()
            }
        }
    }

    fun complete(title: String, memo: String, filledDataList: List<WorkoutSetInfo>) {
        val workout = Workout(title = title, memo = memo)

        // Workout 삽입 및 삽입된 Workout의 ID 반환
        // 반환된 ID를 기반으로 DB에서 Workout과 WorkoutList는 1:N 기반으로 저장됨
        val workoutId = dao.insertWorkout(workout)
        val completedList = filledDataList.map { setInfo -> // workoutId를 기반으로 새 리스트 리턴
            setInfo.copy(parentWorkoutId = workoutId)
        }
        dao.insertSetInfoList(completedList)
    }

    fun getList() = setInfoList.toList()
}