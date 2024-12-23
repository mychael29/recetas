package com.exercise.recetas.utils

import androidx.activity.OnBackPressedCallback

typealias BackDestination = () -> Unit

fun BaseFragment.backNavigation(navController: BackDestination) {
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController()
            }
        }
    requireActivity().onBackPressedDispatcher.addCallback(this, callback)
}