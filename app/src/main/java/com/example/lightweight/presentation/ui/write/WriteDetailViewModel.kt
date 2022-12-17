package com.example.lightweight.presentation.ui.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.data.repository.WriteDetailRepository

class WriteDetailViewModel(
    val repository: WriteDetailRepository,
    val workout: String): ViewModel() {

    private var _items: MutableLiveData<List<WorkoutSetInfo>> = MutableLiveData()
    val items: LiveData<List<WorkoutSetInfo>>
        get() = _items


}