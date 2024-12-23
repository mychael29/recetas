package com.exercise.domain.usecase

import com.exercise.domain.entity.RecipeEntity

class GetUpdatedRecipesUseCase(
    private val getRecipesNewUseCase: GetRecipesNewUseCase,
    private val getRecipesFavoriteUseCase: GetRecipesFavoriteUseCase
) {
    suspend fun execute(): List<RecipeEntity> {

        val recipesFromApi = getRecipesNewUseCase.execute()

        val recipesFromDb = getRecipesFavoriteUseCase.execute()

        return recipesFromApi.map { recipe ->
            val isFavorite = recipesFromDb.any { it.id == recipe.id }
            recipe.copy(isFavorite = isFavorite)
        }
    }
}