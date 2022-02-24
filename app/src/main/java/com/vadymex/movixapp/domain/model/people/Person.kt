package com.vadymex.movixapp.domain.model.people

import com.vadymex.movixapp.domain.model.Image

data class Person(
    val birthday: String,
    val deathday: String,
    val gender: String,
    val id: Int,
    val image: Image,
    val name: String,
    val updated: Int,
    val url: String
)