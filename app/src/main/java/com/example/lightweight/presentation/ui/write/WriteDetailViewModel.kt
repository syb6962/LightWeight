package com.example.lightweight.presentation.ui.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.lightweight.data.db.entity.WorkoutList
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.data.repository.WriteDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WriteDetailViewModel(
    private val repository: WriteDetailRepository
    ): ViewModel() {

    private var _items: MutableStateFlow<List<WorkoutSetInfo>> = MutableStateFlow(listOf())
    val items = _items.asStateFlow()

    fun add() {
        viewModelScope.launch {
            _items.value = repository.add()

        }
    }
}