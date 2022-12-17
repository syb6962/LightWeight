package com.example.lightweight.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Detail(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val set: Int,
    var weight: String = "",
    var reps: String = "") {

    companion object {
        var title: String = ""
        var unit: String = "kg"
        val memo = ""
    }
}
