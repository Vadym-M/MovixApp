package com.vadymex.movixapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadymex.movixapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var comedyAdapter: HomeAdapter
    private lateinit var actionAdapter: HomeAdapter

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
        homeAdapter = HomeAdapter()
        actionAdapter = HomeAdapter()
        comedyAdapter = HomeAdapter()
        binding.mainRecyclerView.apply {

            adapter = homeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                homeAdapter.movies = movies
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