package com.example.randomjoke.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(joke: JokeEntity)

    @Query("select * from jokeentity")
    fun getJokes(): LiveData<List<JokeEntity>>

    @Delete(entity = JokeEntity::class)
    fun deleteJoke(joke: JokeEntity)

}


@Database(entities = [JokeEntity::class], version = 1, exportSchema = false)
abstract class JokeDatabase: RoomDatabase(){
    abstract val jokeDao: JokeDao
}

private lateinit var INSTANCE: JokeDatabase

fun getDatabase(context: Context): JokeDatabase {
    synchronized(JokeDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                JokeDatabase::class.java,
            "jokes").build()
        }
    }
    return INSTANCE
}