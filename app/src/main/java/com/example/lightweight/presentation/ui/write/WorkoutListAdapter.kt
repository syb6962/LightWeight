package com.example.lightweight.presentation.ui.write

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lightweight.R
import com.example.lightweight.databinding.ItemWorkoutBinding

/*****************************************************
 * 운동 리스트 탭의 viewpager의 page 내용을 구성하는 어댑터 *
 *****************************************************/
class WorkoutListAdapter(val setVal : (String) -> Unit) : RecyclerView.Adapter<WorkoutListAdapter.ViewHolder>() {
    private var items = listOf<String>()
    private lateinit var mListener : OnItemClickListener;

    interface OnItemClickListener {
        fun onItemClick(workout: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun addItems(items: List<String>?) {
        // == Collections.sort(items), 아이템 사전편찬순 정렬
        this.items = items?.sorted() ?: throw IllegalAccessError("아무것도 들어있지않음")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    //TODO: 아이템이 TextView 하나라서 데이터 바인딩을 안한것같은데 하는것 고려해보기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return ViewHolder(ItemWorkoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), context = parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(val binding: ItemWorkoutBinding, context: Context)
        : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.workout.setOnClickListener { view ->

//                val page = PageState.AddRoutine()
//                setVal(workout)
                mListener.onItemClick("workout")

//                val action = WorkoutListTabFragmentDirections
//                    .actionWorkoutListTabFragmentToWriteDetailFragment(workout)
//                view.findNavController().navigate(action)

//                Navigation.findNavController(view).navigate(R.id.action_workoutListTabFragment_to_writeRoutineFragment22)
            }
        }

        fun bind(item: String) {
            binding.workout.text = item
        }
    }
}