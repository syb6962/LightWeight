package com.example.lightweight.presentation.ui.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lightweight.R
import com.example.lightweight.WorkoutApplication
import com.example.lightweight.databinding.FragmentWriteDetailBinding
import com.example.lightweight.presentation.viewmodel.WriteDetailViewModelFactory

class WriteDetailFragment :  Fragment() {
    private var _binding : FragmentWriteDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WriteDetailViewModel by viewModels {
        WriteDetailViewModelFactory(
            (requireActivity().application as WorkoutApplication).writeDetailRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWriteDetailBinding.inflate(inflater, container, false)

        binding.btn.setOnClickListener {
            findNavController().navigate(R.id.action_writeDetailFragment_to_addRoutineFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}