package com.example.lightweight.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/******************************************
 * 운동목록(WorkoutList)를 위한 TypeConverter *
 *******************************************/

class WorkoutListTypeConverter {
    @TypeConverter // DB에 저장(DB는 json 형태로 저장됨 아마도.)
    fun listToJson(value: List<String>?) : String {
        return Gson().toJson(value) // 지정한 타입의 데이터를 Json 형식으로 변환
    }

    @TypeConverter // DB 내용을 가져옴. json 에서 Gson으로 알맞게 변환
    fun jsonToList(value: String) : List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type) // Json 데이터를 지정한 타입으로 변환
    }
}