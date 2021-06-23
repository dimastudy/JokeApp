package com.example.randomjoke.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomjoke.joke.Joke

@Entity
data class JokeEntity constructor(
    @PrimaryKey
    val number: String,
    val language: String,
    val insult: String,
    val created: String,
    val shown: String,
    val createdby: String,
    val active: String,
    val comment: String
)

fun List<JokeEntity>.asDomainModel(): List<Joke> {
    return map {
        Joke(
            number = it.number,
            language = it.language,
            insult = it.insult,
            created = it.created,
            shown = it.shown,
            createdby = it.createdby,
            active = it.active,
            comment = it.comment
        )
    }

}
