package com.example.lightweight.presentation.ui.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lightweight.R
import com.example.lightweight.databinding.FragmentAddRoutineBinding

class AddRoutineFragment : Fragment() {
    private var _binding: FragmentAddRoutineBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddRoutineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRoutineBinding.inflate(inflater, container, false)

        binding.addRoutine.setOnClickListener {
            findNavController().navigate(R.id.action_addRoutineFragment_to_workoutListTabFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}