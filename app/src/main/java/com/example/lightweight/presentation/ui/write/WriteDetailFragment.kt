package com.example.lightweight.presentation.ui.write

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lightweight.R
import com.example.lightweight.WorkoutApplication
import com.example.lightweight.data.WorkoutUnit
import com.example.lightweight.databinding.FragmentWriteDetailBinding
import com.example.lightweight.presentation.viewmodel.WriteDetailViewModelFactory
import kotlinx.coroutines.launch

class WriteDetailFragment :  Fragment() {
    private var _binding : FragmentWriteDetailBinding? = null
    private val binding get() = _binding!!
    private val args: WriteDetailFragmentArgs by navArgs()
    private lateinit var adapter: DetailAdapter
    private lateinit var callback : OnBackPressedCallback
    private val viewModel: WriteDetailViewModel by viewModels {
        WriteDetailViewModelFactory(
            (requireActivity().application as WorkoutApplication).writeDetailRepo
        )
    }

    private lateinit var mWorkoutTitle: String
    private var mMemo: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 뒤로가기
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().let {
                    viewModel.clearList()
                    it.navigate(R.id.action_backPress_writeDetail_to_addRoutine)
                }
            }
        }
        activity?.onBackPressedDispatcher!!.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWorkoutTitle = args.workout.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWriteDetailBinding.inflate(inflater, container, false)

        binding.apply {
            workoutTitle.text = args.workout
            adapter = DetailAdapter()
            rv.adapter = adapter
            rv.itemAnimator = null

            // 세트 추가
            addSet.setOnClickListener {
                viewModel.addSet()
//                findNavController().navigate(R.id.action_writeDetailFragment_to_addRoutineFragment)
            }

            // 세트 삭제
            deleteSet.setOnClickListener {
                viewModel.deleteSet()
            }

            // 단위 변경
            unitToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if(isChecked) {
                    when(checkedId) {
                        R.id.kg -> viewModel.changeUnit(WorkoutUnit.kg) // TODO: resource로 고치기. R.~어쩌고하는거
                        R.id.lb -> viewModel.changeUnit(WorkoutUnit.lbs)
                    }
                }
            }

            // 메모
            memo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    // 메모 작성줄 3줄 제한
                    val editTextRowCount = memo.lineCount
                    if (editTextRowCount > 3) {
                        memo.text?.delete(memo.selectionEnd - 1, memo.selectionStart)
                    }
                    mMemo = memo.text.toString()
                }
            })

            // 작성 완료
            complete.setOnClickListener {
                viewModel.complete(mWorkoutTitle, mMemo)
                findNavController().navigate(R.id.action_writeDetailFragment_to_addRoutineFragment)
            }
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { list ->
                    adapter.submitList(list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        callback.remove()
    }
}