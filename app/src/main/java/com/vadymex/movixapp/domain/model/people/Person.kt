package com.vadymex.movixapp.domain.model.people

data class Person(
    val birthday: String,
    val deathday: String,
    val gender: String,
    val id: Int,
    val image: ImagePerson,
    val name: String,
    val updated: Int,
    val url: String
)