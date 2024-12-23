package com.exercise.domain.repository

import com.exercise.domain.entity.RecipeEntity

interface RecipeRepository {
    suspend fun getRecipes(): List<RecipeEntity>
    suspend fun getFavoriteRecipes(): List<RecipeEntity>
    suspend fun addRecipeToFavorites(recipeEntity: RecipeEntity)
    suspend fun removeRecipeFromFavorites(recipeEntity: RecipeEntity)
}