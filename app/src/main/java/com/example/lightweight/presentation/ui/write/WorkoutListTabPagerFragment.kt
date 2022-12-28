package com.example.lightweight.presentation.ui.write

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lightweight.WorkoutApplication
import com.example.lightweight.data.PageState
import com.example.lightweight.databinding.FragmentWorkoutListTabPagerBinding
import com.example.lightweight.domain.BodyPart
import com.example.lightweight.presentation.viewmodel.WorkoutListViewModel
import com.example.lightweight.presentation.viewmodel.WorkoutListViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/***********************************************
 *  WorkoutListTabFragment.kt 의 내용을 구성하는 **
 *  viewpager2의 Page 프래그먼트 *****************
 ***********************************************/

class WorkoutListTabPagerFragment : Fragment(), WorkoutListAdapter.OnItemClickListener {
    private var _binding : FragmentWorkoutListTabPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WorkoutListAdapter
    private lateinit var part: BodyPart
    private val vm : WorkoutListViewModel by viewModels {
        WorkoutListViewModelFactory(
            (requireActivity().application as WorkoutApplication).workoutListRepo
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            part = bundle.getParcelable("part") ?: throw NullPointerException("BodyPart 객체 없음")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorkoutListTabPagerBinding.inflate(inflater, container, false)

        binding.apply {
            adapter = WorkoutListAdapter(::setResult)
            adapter.setOnItemClickListener(this@WorkoutListTabPagerFragment)
            rv.adapter = adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getWorkoutList(part)
        vm.list.observe(viewLifecycleOwner) { list ->
            adapter.addItems(list)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(part: BodyPart) =
            WorkoutListTabPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("part", part)
                }
            }
    }

    private fun setResult(result: String) {
        val navController = findNavController()
        navController.apply {
            previousBackStackEntry?.savedStateHandle?.set("workout", result)
            popBackStack() // 이전화면으로 돌아감.
            //TODO: findNavController().navigate() 를 사용하는것과 어떤차이가있을지?
        }
    }

    // 운동 리스트 클릭 이벤트
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(workout: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            when(PageState.curPageState) {
                is PageState.startWorkout -> {
                    val id = vm.createDailyLog(part)
                    val action = WorkoutListTabFragmentDirections
                        .actionWorkoutListTabFragmentToWriteDetailFragment(id, workout)
                    findNavController().navigate(action)
                }
                is PageState.addWorkout ->
                    Log.i("PageState", PageState.curPageState.getState())
                is PageState.editWorkout -> Log.i("PageState", "${PageState.curPageState}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}