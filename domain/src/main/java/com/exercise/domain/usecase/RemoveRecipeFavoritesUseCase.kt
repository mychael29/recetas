package com.exercise.domain.usecase

import com.exercise.domain.entity.RecipeEntity
import com.exercise.domain.repository.RecipeRepository

class RemoveRecipeFavoritesUseCase(
    private val recipeRepository: RecipeRepository
) {

    suspend fun execute(recipeEntity: RecipeEntity) {
        return recipeRepository.removeRecipeFromFavorites(recipeEntity)
    }

}