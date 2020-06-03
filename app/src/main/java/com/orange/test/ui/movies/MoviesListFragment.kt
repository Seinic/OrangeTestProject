package com.orange.test.ui.movies

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.orange.test.R
import com.orange.test.data.model.Movie
import com.orange.test.util.pager.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.popular_tab_name)
                1 -> getString(R.string.top_tab_name)
                else -> "error"
            }
        }.attach()


    }

    fun navigateToDetails(movie: Movie){
        findNavController().navigate(
            R.id.action_moviesListFragment_to_movieDetailsFragment,
            bundleOf("id" to movie.id)
        )
    }
}