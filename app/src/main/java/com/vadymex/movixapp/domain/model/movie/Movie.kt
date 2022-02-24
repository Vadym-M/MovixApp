package com.vadymex.movixapp.domain.model.movie

import com.vadymex.movixapp.domain.model.Image

data class Movie(
    val averageRuntime: Int,
    val ended: String,
    val genres: List<String>,
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val officialSite: String,
    val premiered: String,
    val runtime: Int,
    val status: String,
    var summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val weight: Int
)