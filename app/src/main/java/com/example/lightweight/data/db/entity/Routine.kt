package com.example.lightweight.data.db.entity

/******************************************************************
 * 뷰에 뿌려질 클래스.
 * WorkoutWithSets 클래스가 이 클래스로 가공되어 짐 *
 ******************************************************************/
data class Routine(
    var workout: Workout,
    var sets: List<WorkoutSetInfo>,
    var isExpandable: Boolean = false
)

data class ParentItem(
    var workout: String="sex",
    val list: List<String>, // ChildItem
    var isExpandable : Boolean = false
)