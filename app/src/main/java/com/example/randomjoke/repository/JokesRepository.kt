package com.example.randomjoke.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.randomjoke.database.JokeDatabase
import com.example.randomjoke.database.asDomainModel
import com.example.randomjoke.joke.Joke
import com.example.randomjoke.network.JokeApi
import com.example.randomjoke.network.JokeNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokesRepository(private val database: JokeDatabase) {

    val jokes: LiveData<List<Joke>> = Transformations.map(database.jokeDao.getJokes()){
        it.asDomainModel()
    }


    suspend fun addJokeToFavorite(joke: JokeNetwork){
        withContext(Dispatchers.IO){
            database.jokeDao.insertJoke(joke.transformToDatabaseEntity())
        }
    }

}