package com.exercise.recetas.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val image: String = "",
    val description: String = "",
    var isFavorite: Boolean = false
): Parcelable