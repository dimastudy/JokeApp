package com.example.randomjoke.jokelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randomjoke.databinding.FragmentJokesListBinding
import com.example.randomjoke.viewmodels.JokesListViewModel

class JokeListFragment: Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentJokesListBinding.inflate(inflater)
        val viewModel = ViewModelProvider(this).get(JokesListViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvListJokes.adapter = JokesListAdapter()
        binding.rvListJokes.setHasFixedSize(true)

        return binding.root
    }
}