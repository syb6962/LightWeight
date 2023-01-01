package com.example.lightweight.data.db

sealed class UnitState(private val name: String) {

    fun getUnitName() = name

    class UnitKg(unit: String): UnitState(unit)
    class UnitLbs(unit: String): UnitState(unit)

//    companion object {
//        var curUnit: UnitState = UnitKg() // 현재 유닛 단위. 기본값: kg
//    }
}