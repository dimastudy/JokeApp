package com.example.randomjoke.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.randomjoke.database.getDatabase
import com.example.randomjoke.repository.JokesRepository

class JokesListViewModel(application: Application): AndroidViewModel(application) {

    val repository = JokesRepository(getDatabase(application))

    val jokesList = repository.jokes


}