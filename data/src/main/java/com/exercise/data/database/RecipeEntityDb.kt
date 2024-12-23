package com.exercise.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class RecipeEntityDb(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val description: String
)