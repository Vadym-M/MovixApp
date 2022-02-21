package com.vadymex.movixapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadymex.movixapp.adapter.MovieAdapter
import com.vadymex.movixapp.databinding.FragmentHomeBinding
import com.vadymex.movixapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var comedyAdapter: MovieAdapter
    private lateinit var actionAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViews()
    }

    private fun setUpRecyclerViews() {
        movieAdapter = MovieAdapter()
        actionAdapter = MovieAdapter()
        comedyAdapter = MovieAdapter()
        binding.mainRecyclerView.apply {

            adapter = movieAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                movieAdapter.movies = movies
            }
        }

        binding.actionRecyclerView.apply {

            adapter = actionAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.moviesAction.observe(viewLifecycleOwner) { movies ->
                actionAdapter.movies = movies
            }
        }

        binding.comedyRecyclerView.apply {

            adapter = comedyAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.moviesComedy.observe(viewLifecycleOwner) { movies ->
                comedyAdapter.movies = movies
            }
        }
    }

}