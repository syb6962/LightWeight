package com.example.lightweight.presentation.ui.write

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.databinding.ItemWorkoutDetailBinding
import com.example.lightweight.setTextIfDifferent

class DetailAdapter : ListAdapter<WorkoutSetInfo, DetailAdapter.ViewHolder >(DetailDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWorkoutDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
            false
            )
        )
    }

    // 스크롤시 EditText의 값이 랜덤하게 바뀌는 현상 해결 방법
    // 하지만 이 방법은 퍼포먼스(성능저하) 측면에서 좋지 않다는 답변이 있음
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

        fun bind(item: WorkoutSetInfo) {
            item.weight = binding.weight.text.toString()
            item.reps = binding.rep.text.toString()
            Log.i("무게확인", "Weight: ${item.weight}, Rep: ${item.reps}")
//            binding.weight.also {
//                it.setTextIfDifferent(item.weight)
////                it.setText(item.weight)
////                it.addTextChangedListener(weightTextWatcher)
//            }
//            binding.rep.also {
////                it.setText(item.reps)
//                it.setTextIfDifferent(item.reps)
////                it.addTextChangedListener(repTextWatcher)
//            }
        }
    }
}