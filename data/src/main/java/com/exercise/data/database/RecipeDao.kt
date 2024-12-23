package com.exercise.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(recipe: RecipeEntityDb)

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<RecipeEntityDb>

    @Delete
    suspend fun deleteFavorite(recipe: RecipeEntityDb)
}