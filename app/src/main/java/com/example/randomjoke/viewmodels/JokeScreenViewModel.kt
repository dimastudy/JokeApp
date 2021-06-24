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

    private val _navigateToListFavorite = MutableLiveData<Boolean>()
    val navigateToListFavorite: LiveData<Boolean>
        get() = _navigateToListFavorite

    val repository = JokesRepository(getDatabase(application))

    fun addCurrentJokeToFavorite(){
        viewModelScope.launch {
            if(currentJoke.value != null) {
                repository.addJokeToFavorite(currentJoke.value!!)
                _isButtonVisible.value = true
            }
        }
    }

    fun navigateToListScreen(){
        _navigateToListFavorite.value = true
    }

    fun doneNavigateToListScreen(){
        _navigateToListFavorite.value = false
    }




    private val _isButtonVisible = MutableLiveData<Boolean>()
    val isButtonVisible: LiveData<Boolean>
        get() = _isButtonVisible


    fun newJokeDone(){
        _isButtonVisible.value = false
    }



    init {
        getJokeFromService()
    }


    fun getJokeFromService() {
        viewModelScope.launch {
            _currentJoke.value = JokeApi.retrofitService.getJoke()
            newJokeDone()
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
