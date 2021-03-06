package com.example.randomjoke.joke

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.randomjoke.databinding.FragmentJokeScreenBinding
import com.example.randomjoke.viewmodels.JokeScreenViewModel

class JokeFragment: Fragment() {

    private val viewModel: JokeScreenViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,JokeScreenViewModel.Factory(activity.application)).get(JokeScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentJokeScreenBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        viewModel.currentJoke.observe(viewLifecycleOwner, Observer {
            if(null != it){
                binding.apply {
                    tvJokeEnglish.text = it.insult
                }
            }
        })


        viewModel.navigateToListFavorite.observe(viewLifecycleOwner, Observer {
            if (it){
                this.findNavController().navigate(JokeFragmentDirections.actionJokeFragmentToJokeListFragment())
                viewModel.doneNavigateToListScreen()
            }
        })

        viewModel.isButtonVisible.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.btnAddJokeToFavorite.visibility = View.INVISIBLE
            }
            else{
                binding.btnAddJokeToFavorite.visibility = View.VISIBLE
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}