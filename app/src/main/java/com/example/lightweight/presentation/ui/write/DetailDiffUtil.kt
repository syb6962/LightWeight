package com.example.lightweight.presentation.ui.write

import androidx.recyclerview.widget.DiffUtil
import com.example.lightweight.data.db.entity.WorkoutSetInfo

class DetailDiffUtil : DiffUtil.ItemCallback<WorkoutSetInfo>() {
    override fun areItemsTheSame(oldItem: WorkoutSetInfo, newItem: WorkoutSetInfo): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: WorkoutSetInfo, newItem: WorkoutSetInfo): Boolean {
        return oldItem == newItem
    }
}