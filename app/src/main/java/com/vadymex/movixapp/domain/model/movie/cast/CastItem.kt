package com.vadymex.movixapp.domain.model.movie.cast

import com.vadymex.movixapp.domain.model.people.Person

data class CastItem(
    val character: Character,
    val person: Person,
    val self: Boolean,
    val voice: Boolean
)