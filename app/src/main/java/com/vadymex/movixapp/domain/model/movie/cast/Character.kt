package com.vadymex.movixapp.domain.model.movie.cast

import com.vadymex.movixapp.domain.model.Image

data class Character(
    val id: Int,
    val image: Image,
    val name: String,
    val url: String
)