package com.example.edvora_rides.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return NearestRidesFragment()
            }
            1 -> {
                return UpcomingRidesFragment()
            }
            2 -> {
                return PastRidesFragment()
            }
            3 -> {
                return FiltersFragment()
            }

            else -> {
                return NearestRidesFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Nearest"
            }
            1 -> {
                return "Upcoming"
            }
            2 -> {
                return "Past"
            }
            3 -> {
                return "Filters"
            }
        }
        return super.getPageTitle(position)
    }
}