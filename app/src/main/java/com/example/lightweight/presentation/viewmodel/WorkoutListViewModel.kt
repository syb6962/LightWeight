package com.example.lightweight.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lightweight.data.repository.WorkoutListRepository
import com.example.lightweight.domain.BodyPart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutListViewModel(
    private val repository: WorkoutListRepository
) : ViewModel() {

    private var _list = MutableLiveData<List<String>>()
    val list: LiveData<List<String>>
        get() = _list

    fun getList(part: BodyPart) {
        viewModelScope.launch(Dispatchers.IO) {
            _list.postValue(repository.getWorkoutList(part))
        }
    }
}