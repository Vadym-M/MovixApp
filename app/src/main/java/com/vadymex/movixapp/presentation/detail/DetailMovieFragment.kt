package com.vadymex.movixapp.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vadymex.movixapp.R
import com.vadymex.movixapp.databinding.FragmentDetailMovieBinding
import com.vadymex.movixapp.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var seasonAdapter: DetailSeasonsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)

        init()
        val bundle = arguments
        val id = bundle?.getString("key") ?: "1"
        viewModel.initMovieId(id)
        return binding.root
    }

    private fun init(){
       viewModel.movie.observe(viewLifecycleOwner){ movie ->
           binding.apply {
               detailMovieGenre.text = movie.genres.toString()
               detailMovieRate.text= "8.8"
               detailMovieSummary.text = movie.summary
               detailMovieTitle.text = movie.name
               Glide.with(root)
                   .load(movie.image.original)
                   .into(imageView)
           }
       }
        seasonAdapter = DetailSeasonsAdapter()
        binding.detailMovieSeasons.apply {
            adapter = seasonAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        viewModel.seasons.observe(viewLifecycleOwner){ seasons ->
            seasonAdapter.seasons = seasons
        }
    }

}