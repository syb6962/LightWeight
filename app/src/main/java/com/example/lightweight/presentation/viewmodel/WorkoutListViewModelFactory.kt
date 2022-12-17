package com.example.lightweight.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lightweight.data.repository.WorkoutListRepository

class WorkoutListViewModelFactory(
    private val repository: WorkoutListRepository
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WorkoutListViewModel::class.java))
            return WorkoutListViewModel(repository) as T
        throw IllegalAccessException("Unknown ViewModel Class")
    }
}