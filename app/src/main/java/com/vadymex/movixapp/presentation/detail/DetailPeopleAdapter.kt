package com.vadymex.movixapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vadymex.movixapp.databinding.ItemMovieBinding
import com.vadymex.movixapp.databinding.ItemPersonBinding
import com.vadymex.movixapp.domain.model.movie.Movie
import com.vadymex.movixapp.domain.model.people.Person

class DetailPeopleAdapter: RecyclerView.Adapter<DetailPeopleAdapter.ViewHolder>() {
    var onItemClick: ((String) -> Unit)? = null
    inner class ViewHolder(val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(people[adapterPosition].id.toString())
            }
        }
    }
    private val diffcalback = object : DiffUtil.ItemCallback<Person>(){
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return  newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffcalback)
    var people: List<Person>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = people[position]
        holder.binding.apply {
            personName.text = currentPerson.name

            currentPerson.image?.let {
                Glide.with(holder.binding.root)
                    .load(currentPerson.image.original)
                    .into(personImage)
            }
        }
    }

    override fun getItemCount() = people.size
}