package com.example.randomjoke.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JokeDao {

    @Insert
    suspend fun insertJoke(joke: JokeEntity)

    @Query("select * from jokeentity")
    fun getJokes(): LiveData<List<JokeEntity>>

    @Delete
    suspend fun deleteJoke(joke: JokeEntity)

}


@Database(entities = [JokeEntity::class], version = 1)
abstract class JokeDatabase() : RoomDatabase(){
    abstract val jokeDao: JokeDao
}

private lateinit var INSTANCE: JokeDatabase

fun getDatabase(context: Context): JokeDatabase {
    synchronized(JokeDatabase::class){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                JokeDatabase::class.java,
            "jokes").build()
        }
    }
    return INSTANCE
}