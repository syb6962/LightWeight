package com.example.lightweight.presentation.ui.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.lightweight.R
import com.example.lightweight.data.PageState
import com.example.lightweight.databinding.FragmentDailyWorkoutLogBinding

class DailyWorkoutLogFragment : Fragment() {

    private var _binding: FragmentDailyWorkoutLogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dailyWorkoutLogViewModel =
            ViewModelProvider(this).get(DailyWorkoutLogViewModel::class.java)

        _binding = FragmentDailyWorkoutLogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dailyWorkoutLogViewModel.text.observe(viewLifecycleOwner) {

        }

        binding.startWorkout.setOnClickListener {
            PageState.curPageState = PageState.startWorkout("startWorkout")
            val action = DailyWorkoutLogFragmentDirections
                .actionDailyWorkoutLogFragmentToWorkoutListTabFragment(PageState.startWorkout("DailyLog"))
            findNavController().navigate(action)
        }

        binding.editWorkout.setOnClickListener {
            val action = DailyWorkoutLogFragmentDirections
                .actionDailyWorkoutLogFragmentToWorkoutListTabFragment(PageState.startWorkout("DailyLog"))
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}