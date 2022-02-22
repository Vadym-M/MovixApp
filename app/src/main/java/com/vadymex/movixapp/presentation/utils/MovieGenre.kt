package com.vadymex.movixapp.presentation.utils

sealed class MovieGenre<T>(data:T?) {
    class Drama<T>(data: T?) : MovieGenre<T>(data)
    class ScienceFiction<T>(data: T?) : MovieGenre<T>(data)
    class Thriller<T>(data: T?) : MovieGenre<T>(data)
    class Action<T>(data: T?) : MovieGenre<T>(data)
    class Crime<T>(data: T?) : MovieGenre<T>(data)
    class Horror<T>(data: T?) : MovieGenre<T>(data)
    class Romance<T>(data: T?) : MovieGenre<T>(data)
    class Adventure<T>(data: T?) : MovieGenre<T>(data)
    class Espionage<T>(data: T?) : MovieGenre<T>(data)
    class Music<T>(data: T?) : MovieGenre<T>(data)
    class Mystery<T>(data: T?) : MovieGenre<T>(data)
}