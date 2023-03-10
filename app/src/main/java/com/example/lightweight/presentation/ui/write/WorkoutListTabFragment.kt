package com.example.lightweight.presentation.ui.write

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.lightweight.data.PageState
import com.example.lightweight.databinding.FragmentWorkoutListTabBinding
import com.google.android.material.tabs.TabLayoutMediator


/***************
 *  운동목록 Tab *
 ***************/
class WorkoutListTabFragment : Fragment() {
    private var _binding: FragmentWorkoutListTabBinding? = null
    private val binding get() = _binding!!
    private val args : WorkoutListTabFragmentArgs by navArgs() // TODO: 필요없어보임
    private lateinit var tabAdapter: WorkoutListTabAdapter
    private val list by lazy { listOf("가슴","등","하체", "어깨", "이두", "삼두", "복근") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutListTabBinding.inflate(inflater, container, false)
        tabAdapter = WorkoutListTabAdapter(this)

        binding.apply {
            pager.adapter = tabAdapter
            // TabLayout 을 viewpager2에 연결
            TabLayoutMediator(tablayout, pager) { tab, position ->
                tab.text = list[position]
            }.attach()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}