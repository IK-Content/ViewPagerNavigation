package com.jasontsh.interviewkickstart.viewpagernavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE




class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val pager = root.findViewById<ViewPager2>(R.id.pager)
        val tabLayout = root.findViewById<TabLayout>(R.id.tab_layout)
        val viewModel: ViewPagerViewModel by activityViewModels()
        val pagerAdapter = ScreenSlidePagerAdapter(this, viewModel)
        pager.adapter = pagerAdapter
        // the lines below have to be called after adapter is assigned
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = "Item ${(position + 1)}"
        }.attach()
        viewModel.stepList.observe(this, {
            pager.adapter = ScreenSlidePagerAdapter(this, viewModel)
            pager.setCurrentItem(viewModel.currentPosition.value ?: 0, false)
        })
        return root
    }

    private inner class ScreenSlidePagerAdapter(fragment: Fragment, val viewModel: ViewPagerViewModel) :
        FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            val step =
                viewModel.stepList.value?.get(position)
                    ?: return StepFragment.newInstance(position + 1)
            return if (step > 0) {
                DestinationFragment.newInstance(step, position + 1)
            } else {
                StepFragment.newInstance(position + 1)
            }
        }

    }
}