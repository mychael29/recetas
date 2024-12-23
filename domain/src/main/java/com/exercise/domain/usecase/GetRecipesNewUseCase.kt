package com.exercise.domain.usecase

import com.exercise.domain.entity.RecipeEntity
import com.exercise.domain.repository.RecipeRepository

class GetRecipesNewUseCase(
    private val recipeRepository: RecipeRepository
) {

    suspend fun execute(): List<RecipeEntity> {
        return recipeRepository.getRecipes()
    }
}
