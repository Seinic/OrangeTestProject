package com.orange.test.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.orange.test.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment: Fragment() {
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        binding.viewModel = viewModel
        viewModel.movieId = requireArguments().getInt("id", -1)
        return binding.root
    }
}