package com.vadymex.movixapp.presentation.utils

import androidx.fragment.app.Fragment

interface Navigator {
    fun onItemClicked(onItemShow: OnItemShow)
}
fun Fragment.navigator() :Navigator{
    return requireActivity() as Navigator
}