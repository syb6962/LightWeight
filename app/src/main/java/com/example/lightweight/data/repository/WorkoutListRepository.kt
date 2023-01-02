package com.example.lightweight.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.lightweight.data.db.dao.WorkoutDao
import com.example.lightweight.data.db.entity.DailyWorkout
import com.example.lightweight.domain.BodyPart
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WorkoutListRepository(private val dao: WorkoutDao) {

   fun getWorkoutList(part: BodyPart): List<String> {
        return when(part) {
            // getList()의 파라미터인 id는 DB WorkoutList에 저장되어있는 bodyPartId에 대응됨
            // 가슴 ~ 복근까지 각 0~6에 대응
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
        return list.filter { it.bodyPartId == id }.map { it.workout } // WorkoutList에서 운동만 추출
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun createDailyLog(part: BodyPart) : Long {
        val date = LocalDate.now()
        val formatter =
            DateTimeFormatter
                .ofPattern("yyyy/M/dd E요일")
                .format(date)

        val data = DailyWorkout(date = formatter, bodyPart = part.getPart())
        return dao.insertDailyLog(data) // 해당 DailyLog의 Id 리턴
    }
}