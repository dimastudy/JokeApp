package com.example.randomjoke

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randomjoke.joke.Joke
import com.example.randomjoke.jokelist.JokesListAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Joke>?){
    val adapter = recyclerView.adapter as JokesListAdapter
    adapter.submitList(data)
}