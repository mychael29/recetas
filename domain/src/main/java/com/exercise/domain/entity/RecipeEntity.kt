package com.exercise.domain.entity

data class RecipeEntity(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val isFavorite: Boolean
)