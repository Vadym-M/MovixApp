package com.vadymex.movixapp.domain.model.movie.episodes

import com.vadymex.movixapp.domain.model.Image

data class Episode(
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: Image,
    val name: String,
    val number: Int,
    val rating: Rating,
    val runtime: Int,
    val season: Int,
    val summary: String,
    val type: String,
    val url: String
)