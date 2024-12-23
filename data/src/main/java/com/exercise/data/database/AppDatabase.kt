package com.exercise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntityDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}