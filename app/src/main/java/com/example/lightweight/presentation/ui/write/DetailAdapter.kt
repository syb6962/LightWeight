package com.example.lightweight.presentation.ui.write

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.databinding.ItemWorkoutDetailBinding

class DetailAdapter : ListAdapter<WorkoutSetInfo, DetailAdapter.ViewHolder >(DetailDiffUtil()) {

    // DB에 저장하기위한 입력 완료 리스트 리턴
    fun getCompletedListForDB(): MutableList<WorkoutSetInfo> = currentList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWorkoutDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
            false
            )
        )
    }

    /***********************************************
     * https://stackoverflow.com/a/64396998/14415521 *
     ***********************************************/
    override fun getItemViewType(position: Int): Int {
        return position // position만 리턴해주면 됨.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(val binding: ItemWorkoutDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var weightTextWatcher: TextWatcher
        private lateinit var repTextWatcher: TextWatcher

        fun bind(item: WorkoutSetInfo) {
            weightTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun afterTextChanged(w: Editable?) {
                    item.weight = w.toString()
                }
            }

            repTextWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
                override fun afterTextChanged(r: Editable?) {
                    item.reps = r.toString()
                }
            }

            binding.apply {
                set.text = item.set.toString()
                unit.text = item.unit.name
                weight.addTextChangedListener(weightTextWatcher)
                rep.addTextChangedListener(repTextWatcher)
            }

//            binding.weight.removeTextChangedListener(weightTextWatcher)
//            binding.rep.removeTextChangedListener(repTextWatcher)
        }
    }
}