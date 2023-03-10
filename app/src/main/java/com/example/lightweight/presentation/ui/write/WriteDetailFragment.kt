package com.example.lightweight.presentation.ui.write

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
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
import com.example.lightweight.data.db.UnitState
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

    private lateinit var mWorkoutTitle: String // DB에 저장될 운동 이름
    private var mMemo: String = "" // DB에 저장될 메모 내용
    private var mUnitState: UnitState = UnitState.UnitKg("kg") // add() 할때 필요
    private var mDailyId: Long = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 뒤로가기
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().let {
//                    viewModel.clearList()
                    it.navigate(R.id.action_backPress_writeDetail_to_addRoutine)
                }
            }
        }
        activity?.onBackPressedDispatcher!!.addCallback(this, callback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWorkoutTitle = args.workout.toString() // 운동 이름
        mDailyId = args.id // Daily Id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWriteDetailBinding.inflate(inflater, container, false)

        binding.apply {
            workoutTitle.text = mWorkoutTitle
            adapter = DetailAdapter()
            rv.adapter = adapter
            rv.itemAnimator = null

            // 세트 추가
            addSet.setOnClickListener {
                viewModel.addSet(mUnitState)
                Log.i("확인", WorkoutUnit.kg.name)
//                findNavController().navigate(R.id.action_writeDetailFragment_to_addRoutineFragment)
            }

            // 세트 삭제
            deleteSet.setOnClickListener {
                viewModel.deleteSet()
            }

            // 단위 변경
            unitToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if(isChecked && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    changeUnitState(checkedId)
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
                val resultList = adapter.getCompletedListForDB()
                viewModel.complete(mWorkoutTitle, mMemo, resultList, mDailyId)
                findNavController().navigate(R.id.action_writeDetailFragment_to_addRoutineFragment)
            }
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun changeUnitState(checkedId: Int) {
        when(checkedId) {
            R.id.kg -> mUnitState = UnitState.UnitKg(getString(R.string.unit_kg))
            R.id.lb -> mUnitState = UnitState.UnitLbs(getString(R.string.unit_lbs))
        }
        viewModel.changeUnit(mUnitState)
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