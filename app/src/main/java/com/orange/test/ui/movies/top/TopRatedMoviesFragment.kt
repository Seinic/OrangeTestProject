package com.orange.test.ui.movies.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.orange.test.R
import com.orange.test.databinding.FragmentTopRatedMoviesBinding
import com.orange.test.ui.movies.MoviesListFragment
import com.orange.test.util.recycler.MoviesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_movies.*

class TopRatedMoviesFragment: Fragment() {
    private val viewModel: TopRatedMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MoviesRecyclerViewAdapter()
        movies_recycler.adapter = adapter
        adapter.onItemClick = //TODO refactor
            { movie -> (parentFragment as MoviesListFragment).navigateToDetails(movie) }
        adapter.notifyDataSetChanged()
    }
}