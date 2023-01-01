package com.example.lightweight.presentation.ui.write

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lightweight.data.WorkoutUnit
import com.example.lightweight.data.db.UnitState
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.data.repository.WriteDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WriteDetailViewModel(
    private val repository: WriteDetailRepository
    ): ViewModel() {

    private var _items: MutableStateFlow<List<WorkoutSetInfo>> = MutableStateFlow(repository.getList())
    val items = _items.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.N)
    fun changeUnit(unit: UnitState) {
        repository.changeUnit(unit)
        _items.value = repository.getList()
    }

    fun addSet(unit: UnitState) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(unit)
            _items.value = repository.getList()
        }
    }
    fun deleteSet() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete()
            _items.value = repository.getList()
        }
    }

    fun complete(title: String, memo: String, completedList: List<WorkoutSetInfo>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.complete(title, memo, completedList)
        }
    }
}