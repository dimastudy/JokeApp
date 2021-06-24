package com.example.randomjoke.jokelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randomjoke.databinding.ItemJokeBinding
import com.example.randomjoke.joke.Joke

class JokesListAdapter : ListAdapter<Joke, JokesListAdapter.JokesListViewHolder>(DiffCallback) {


    class JokesListViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.tvJokeItem.text = joke.insult
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesListViewHolder {
        return JokesListViewHolder(ItemJokeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: JokesListViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean =
            oldItem.number == newItem.number

    }

}