package com.orange.test.ui.movies.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.orange.test.R
import com.orange.test.data.model.Movie
import com.orange.test.databinding.FragmentPopularMoviesBinding
import com.orange.test.ui.movies.MoviesListFragment
import com.orange.test.util.recycler.MoviesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularMoviesFragment : Fragment() {
    private val viewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
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