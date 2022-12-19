package com.example.lightweight.presentation.ui.write

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lightweight.domain.BodyPart


/********************************
 * viewpager2 페이지 생성 adapter *
 ********************************/
class WorkoutListTabAdapter(f: Fragment) : FragmentStateAdapter(f){
    override fun getItemCount(): Int {
        return 7
    }

    // Tab의 내용 화면 만들기
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> WorkoutListTabPageFragment.newInstance(BodyPart.Chest())
            1 -> WorkoutListTabPageFragment.newInstance(BodyPart.Back())
            2 -> WorkoutListTabPageFragment.newInstance(BodyPart.Leg())
            3 -> WorkoutListTabPageFragment.newInstance(BodyPart.Shoulder())
            4 -> WorkoutListTabPageFragment.newInstance(BodyPart.Biceps())
            5 -> WorkoutListTabPageFragment.newInstance(BodyPart.Triceps())
            else -> WorkoutListTabPageFragment.newInstance(BodyPart.Abs()) // 7번째
        }
    }
}