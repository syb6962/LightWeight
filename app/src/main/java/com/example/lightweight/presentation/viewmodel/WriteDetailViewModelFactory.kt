package com.example.lightweight.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lightweight.data.repository.WriteDetailRepository
import com.example.lightweight.presentation.ui.write.WriteDetailViewModel

class WriteDetailViewModelFactory(
    private val repository: WriteDetailRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WriteDetailViewModel::class.java))
            return WriteDetailViewModel(repository) as T
        throw IllegalAccessException("Unknown ViewModel Class")
    }
}