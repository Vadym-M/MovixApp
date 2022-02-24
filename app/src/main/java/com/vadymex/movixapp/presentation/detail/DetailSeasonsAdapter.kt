package com.vadymex.movixapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vadymex.movixapp.databinding.ItemDetailSeasonsBinding
import com.vadymex.movixapp.domain.model.movie.seasons.Season

class DetailSeasonsAdapter: RecyclerView.Adapter<DetailSeasonsAdapter.ViewHolder>() {
    var onItemClick: ((String) -> Unit)? = null
    inner class ViewHolder(val binding: ItemDetailSeasonsBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(seasons[adapterPosition].id.toString())
            }
        }
    }
    private val diffcalback = object : DiffUtil.ItemCallback<Season>(){
        override fun areItemsTheSame(oldItem: Season, newItem: Season): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Season, newItem: Season): Boolean {
            return  newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffcalback)
    var seasons: List<Season>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDetailSeasonsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSeason = seasons[position]
        holder.binding.apply {
            detailSeasonTitle.text = "Season "+currentSeason.number.toString()
            detailSeasonSummary.text = currentSeason.summary

            currentSeason.image?.let {
                Glide.with(holder.binding.root)
                    .load(currentSeason.image.original)
                    .into(detailSeasonImage)
            }
        }
    }

    override fun getItemCount() = seasons.size
}