package com.example.lightweight.data.db

sealed class UnitState(private val name: String) {
    fun getUnitName() = name

    class UnitKg(unit: String): UnitState(unit)
    class UnitLbs(unit: String): UnitState(unit)
}