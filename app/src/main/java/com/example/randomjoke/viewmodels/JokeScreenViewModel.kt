package com.example.randomjoke.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomjoke.network.JokeNetwork
import com.example.randomjoke.network.JokeApi
import kotlinx.coroutines.launch

class JokeScreenViewModel: ViewModel() {

    private val _currentJoke = MutableLiveData<JokeNetwork>()
    val currentJoke: LiveData<JokeNetwork>
        get() = _currentJoke

    init {
        getJokeFromService()
    }


    fun getJokeFromService() {
        viewModelScope.launch {
            _currentJoke.value = JokeApi.retrofitService.getJoke()
        }
    }


}