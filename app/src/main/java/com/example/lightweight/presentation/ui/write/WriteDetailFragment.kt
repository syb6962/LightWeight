package com.example.lightweight.presentation.ui.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.lightweight.WorkoutApplication
import com.example.lightweight.databinding.FragmentWriteDetailBinding
import com.example.lightweight.presentation.viewmodel.WriteDetailViewModelFactory
import kotlinx.coroutines.launch

class WriteDetailFragment :  Fragment() {
    private var _binding : FragmentWriteDetailBinding? = null
    private val binding get() = _binding!!
    private val args: WriteDetailFragmentArgs by navArgs()
    private lateinit var adapter: DetailAdapter
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
        binding.apply {
            adapter = DetailAdapter()
            rv.adapter = adapter
            rv.itemAnimator = null
            
            // 세트 추가
            add.setOnClickListener {
                viewModel.add()
//                findNavController().navigate(R.id.action_writeDetailFragment_to_addRoutineFragment)
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collect { list ->
                binding.rv.setItemViewCacheSize(list.size)
                adapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}