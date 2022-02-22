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
    private lateinit var homeMovieAdapter: HomeMovieAdapter
    private lateinit var homePeopleAdapter: HomePeopleAdapter


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
        homeMovieAdapter = HomeMovieAdapter()
        homePeopleAdapter = HomePeopleAdapter()

        binding.mainRecyclerView.apply {

            adapter = homeMovieAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                homeMovieAdapter.movies = movies
            }
        }

        binding.comedyRecyclerView.apply {

            adapter = homePeopleAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.people.observe(viewLifecycleOwner) { people ->
                homePeopleAdapter.people = people
            }
        }
    }

}