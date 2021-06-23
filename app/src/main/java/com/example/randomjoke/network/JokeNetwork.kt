package com.example.randomjoke.network

import android.os.Parcelable
import com.example.randomjoke.database.JokeEntity
import com.example.randomjoke.joke.Joke
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JokeNetwork(
    val number: String,
    val language: String,
    val insult: String,
    val created: String,
    val shown: String,
    val createdby: String,
    val active: String,
    val comment: String
):Parcelable {
    
    fun transformToDatabaseEntity(): JokeEntity{
        return JokeEntity(
            number = this.number,
            language = this.language,
            insult = this.insult,
            created = this.created,
            shown = this.shown,
            createdby = this.createdby,
            active = this.active,
            comment = this.comment
        )
    }

    fun transformToDomain(): Joke{
        return Joke(
            number = this.number,
            language = this.language,
            insult = this.insult,
            created = this.created,
            shown = this.shown,
            createdby = this.createdby,
            active = this.active,
            comment = this.comment
        )
    }

}