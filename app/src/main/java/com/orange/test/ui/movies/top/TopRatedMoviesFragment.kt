package com.orange.test.ui.movies.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orange.test.R
import com.orange.test.databinding.FragmentTopRatedMoviesBinding
import com.orange.test.ui.movies.MoviesListFragment
import com.orange.test.util.recycler.MoviesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.fragment_top_rated_movies.*
import kotlinx.android.synthetic.main.fragment_top_rated_movies.movies_recycler

class TopRatedMoviesFragment : Fragment() {
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
        setupRecycler()
    }

    private fun setupRecycler() {
        val adapter = MoviesRecyclerViewAdapter()
        movies_recycler.adapter = adapter
        adapter.onItemClick =
            { movie -> (parentFragment as MoviesListFragment).navigateToDetails(movie) }
        adapter.notifyDataSetChanged()

        movies_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager =
                        recyclerView.layoutManager as LinearLayoutManager?
                    val visibleItemCount = layoutManager?.childCount ?: 0
                    val totalItemCount = layoutManager?.itemCount ?: 0
                    val pastVisibleItems = layoutManager?.findFirstVisibleItemPosition() ?: 0
                    if (!viewModel.isLoading.get() && viewModel.page < viewModel.maxPages) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            viewModel.loadNextPage()
                        }
                    }
                }
            }
        })
    }
}