package com.exercise.recetas.di

import com.exercise.domain.repository.RecipeRepository
import com.exercise.domain.usecase.AddRecipeToFavoritesUseCase
import com.exercise.domain.usecase.GetRecipesFavoriteUseCase
import com.exercise.domain.usecase.GetRecipesNewUseCase
import com.exercise.domain.usecase.GetUpdatedRecipesUseCase
import com.exercise.domain.usecase.RemoveRecipeFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRecipesNewUseCase(recipeRepository: RecipeRepository): GetRecipesNewUseCase {
        return GetRecipesNewUseCase(recipeRepository)
    }

    @Provides
    fun provideGetRecipesFavoriteUseCase(recipeRepository: RecipeRepository): GetRecipesFavoriteUseCase {
        return GetRecipesFavoriteUseCase(recipeRepository)
    }

    @Provides
    fun provideGetUpdatedRecipesUseCase(
        getRecipesNewUseCase: GetRecipesNewUseCase,
        getRecipesFavoriteUseCase: GetRecipesFavoriteUseCase
    ): GetUpdatedRecipesUseCase {
        return GetUpdatedRecipesUseCase(getRecipesNewUseCase, getRecipesFavoriteUseCase)
    }

    @Provides
    fun provideAddRecipeToFavoritesUseCase(recipeRepository: RecipeRepository): AddRecipeToFavoritesUseCase {
        return AddRecipeToFavoritesUseCase(recipeRepository)
    }

    @Provides
    fun provideRemoveRecipeFavoritesUseCase(recipeRepository: RecipeRepository): RemoveRecipeFavoritesUseCase {
        return RemoveRecipeFavoritesUseCase(recipeRepository)
    }

}