package com.jilan.pidio

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val rating: String,
    val photo: String,
    val duration: String,
    val genre: String,
    val trailer: String
) : Parcelable
