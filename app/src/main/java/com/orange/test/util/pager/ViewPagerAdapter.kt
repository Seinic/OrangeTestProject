package com.orange.test.util.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.orange.test.ui.movies.popular.PopularMoviesFragment
import com.orange.test.ui.movies.top.TopRatedMoviesFragment

class ViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        val fragment = TopRatedMoviesFragment()

        return when (position){
            1 -> TopRatedMoviesFragment()
            else -> PopularMoviesFragment()
        }
    }
}