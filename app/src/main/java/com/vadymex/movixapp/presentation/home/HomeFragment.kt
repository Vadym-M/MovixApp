package com.vadymex.movixapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vadymex.movixapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeMovieAdapter: HomeMovieAdapter
    private lateinit var homePeopleAdapter: HomePeopleAdapter
    private lateinit var newestMovieAdapter: HomeNewestMovieAdapter


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
        newestMovieAdapter = HomeNewestMovieAdapter()

        binding.mainRecyclerView.apply {

            adapter = homeMovieAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                homeMovieAdapter.movies = movies
            }
        }

        binding.peopleRecyclerView.apply {

            adapter = homePeopleAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            viewModel.people.observe(viewLifecycleOwner) { people ->
                homePeopleAdapter.people = people
            }
        }

        binding.newMoviesRecyclerView.apply {
            val lm = object : LinearLayoutManager(requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = newestMovieAdapter
            layoutManager = lm
            setHasFixedSize(true)
            viewModel.newestMovies.observe(viewLifecycleOwner) { movies ->
                newestMovieAdapter.movies = movies
            }
        }

        viewModel.randomMovie.observe(viewLifecycleOwner) { movie ->
            Glide.with(requireActivity())
                .load(movie.image.original)
                .into(binding.randomMovieImage)
            binding.randomMovieTitle.text = movie.name
        }


    }

}