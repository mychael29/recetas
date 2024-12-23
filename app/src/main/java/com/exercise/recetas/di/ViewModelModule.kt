package com.exercise.recetas.di

import com.exercise.domain.usecase.AddRecipeToFavoritesUseCase
import com.exercise.domain.usecase.GetRecipesFavoriteUseCase
import com.exercise.domain.usecase.GetUpdatedRecipesUseCase
import com.exercise.domain.usecase.RemoveRecipeFavoritesUseCase
import com.exercise.recetas.presentation.mapper.RecipeMapper
import com.exercise.recetas.presentation.mapper.RecipeMapperImpl
import com.exercise.recetas.presentation.viewmodel.RecipesDetailViewModel
import com.exercise.recetas.presentation.viewmodel.RecipesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMapperRecipes(): RecipeMapper {
        return RecipeMapperImpl()
    }

    @Provides
    fun provideRecipesViewModel(
        getUpdatedRecipesUseCase: GetUpdatedRecipesUseCase,
        getRecipesFavoriteUseCase: GetRecipesFavoriteUseCase,
        addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
        removeRecipeFavoritesUseCase: RemoveRecipeFavoritesUseCase,
        recipeMapper: RecipeMapper
    ): RecipesViewModel {
        return RecipesViewModel(
            getUpdatedRecipesUseCase,
            getRecipesFavoriteUseCase,
            addRecipeToFavoritesUseCase,
            removeRecipeFavoritesUseCase,
            recipeMapper
        )
    }

    @Provides
    fun provideRecipesDetailViewModel(
        addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
        removeRecipeFavoritesUseCase: RemoveRecipeFavoritesUseCase,
        recipeMapper: RecipeMapper
    ): RecipesDetailViewModel {
        return RecipesDetailViewModel(addRecipeToFavoritesUseCase, removeRecipeFavoritesUseCase, recipeMapper)
    }
}