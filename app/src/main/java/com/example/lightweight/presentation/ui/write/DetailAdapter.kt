package com.example.lightweight.presentation.ui.write

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.data.db.entity.WorkoutSetInfo
import com.example.lightweight.databinding.ItemWorkoutDetailBinding

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
        private var weightTextWatcher: TextWatcher? = null
        private var repTextWatcher: TextWatcher? = null

        fun bind(item: WorkoutSetInfo) {
            binding.set.text = item.set.toString() // as로 타입캐스팅이 되어야 다음부터 item.--이 가능해짐
            binding.weight.removeTextChangedListener(weightTextWatcher)
            binding.unit.text = item.unit.name
            binding.rep.removeTextChangedListener(repTextWatcher)

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
                    if(!binding.rep.hasFocus())
                        return
                    item.reps = r.toString()
                }
            }
        }
    }
}