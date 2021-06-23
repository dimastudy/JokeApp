package com.example.randomjoke.network

import android.os.Parcelable
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
}