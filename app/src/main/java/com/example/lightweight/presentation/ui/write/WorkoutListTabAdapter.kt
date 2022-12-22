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

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> WorkoutListTabPagerFragment.newInstance(BodyPart.Chest())
            1 -> WorkoutListTabPagerFragment.newInstance(BodyPart.Back())
            2 -> WorkoutListTabPagerFragment.newInstance(BodyPart.Leg())
            3 -> WorkoutListTabPagerFragment.newInstance(BodyPart.Shoulder())
            4 -> WorkoutListTabPagerFragment.newInstance(BodyPart.Biceps())
            5 -> WorkoutListTabPagerFragment.newInstance(BodyPart.Triceps())
            else -> WorkoutListTabPagerFragment.newInstance(BodyPart.Abs()) // 7번째
        }
    }
}