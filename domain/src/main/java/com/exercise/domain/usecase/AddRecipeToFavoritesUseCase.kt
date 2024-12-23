package com.exercise.domain.usecase

import com.exercise.domain.entity.RecipeEntity
import com.exercise.domain.repository.RecipeRepository

class AddRecipeToFavoritesUseCase(
    private val recipeRepository: RecipeRepository
) {

    suspend fun execute(recipeEntity: RecipeEntity) {
        return recipeRepository.addRecipeToFavorites(recipeEntity)
    }

}