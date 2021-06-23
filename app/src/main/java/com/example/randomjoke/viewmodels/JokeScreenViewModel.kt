package com.example.randomjoke.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.randomjoke.database.getDatabase
import com.example.randomjoke.network.JokeNetwork
import com.example.randomjoke.network.JokeApi
import com.example.randomjoke.repository.JokesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class JokeScreenViewModel(application: Application): AndroidViewModel(application) {

    private val _currentJoke = MutableLiveData<JokeNetwork>()
    val currentJoke: LiveData<JokeNetwork>
        get() = _currentJoke

    val repository = JokesRepository(getDatabase(application))

    fun addCurrentJokeToFavorite(){
        viewModelScope.launch {
            if(currentJoke.value != null) {
                repository.addJokeToFavorite(currentJoke.value!!)
            }
        }
    }


    init {
        getJokeFromService()
    }


    fun getJokeFromService() {
        viewModelScope.launch {
            _currentJoke.value = JokeApi.retrofitService.getJoke()
        }
    }

    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(JokeScreenViewModel::class.java)){
                return JokeScreenViewModel(app) as T
                }
            throw IllegalArgumentException("Cannot to construct viewModel")
        }

    }


}
