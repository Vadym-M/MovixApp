package com.vadymex.movixapp.domain.model.movie.seasons

import com.vadymex.movixapp.domain.model.Image

data class Season(
    val endDate: String,
    val episodeOrder: Int,
    val id: Int,
    val image: Image,
    val name: String,
    val number: Int,
    val premiereDate: String,
    val summary: String,
    val url: String,
)