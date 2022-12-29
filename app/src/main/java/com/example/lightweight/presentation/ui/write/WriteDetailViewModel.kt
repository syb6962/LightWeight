package com.example.lightweight.presentation.ui.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lightweight.data.WorkoutUnit
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

    fun changeUnit(unit: WorkoutUnit) {
        repository.changeUnit(unit)
        _items.value = repository.getList()
    }

    fun addSet() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add()
            _items.value = repository.getList()
        }
    }
    fun deleteSet() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete()
            _items.value = repository.getList()
        }
    }

    fun complete(title: String, memo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.complete(title, memo)
        }
    }
}