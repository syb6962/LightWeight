package com.example.lightweight.presentation.ui.write

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.databinding.ItemWorkoutDetailBinding

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    private var items = listOf<WorkoutSetInfo>()

    fun addItems(items: List<WorkoutSetInfo>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWorkoutDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ItemWorkoutDetailBinding)
        : RecyclerView.ViewHolder(binding.root) {


    }
}