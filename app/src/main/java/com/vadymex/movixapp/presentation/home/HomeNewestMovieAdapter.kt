package com.vadymex.movixapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vadymex.movixapp.databinding.ItemNewestMoviesBinding
import com.vadymex.movixapp.domain.model.movie.Movie

class HomeNewestMovieAdapter: RecyclerView.Adapter<HomeNewestMovieAdapter.ViewHolder>() {
    var onItemClick: ((String) -> Unit)? = null
    inner class ViewHolder(val binding: ItemNewestMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition].id.toString())
            }
        }
    }
    private val diffcalback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return  newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffcalback)
    var movies: List<Movie>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNewestMoviesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.binding.apply {
            newestMovieTitle.text = currentMovie.name
            newestSummaryMovie.text = currentMovie.summary

            Glide.with(holder.binding.root)
                .load(currentMovie.image.original)
                .into(newestMovieImage)
        }
    }

    override fun getItemCount() = movies.size
}