package com.vadymex.movixapp.presentation.utils

sealed class Resource<T>(val data: T?, val msg: String? = null) {
    class Success<T>(data: T?):Resource<T>(data)
    class Error<T>(msg: String?, data: T? = null):Resource<T>(data, msg)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

fun String.removeHTMLTags():String{
   return this.replace("<.*?>".toRegex(), "")
}