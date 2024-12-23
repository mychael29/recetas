package com.exercise.recetas.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.exercise.domain.entity.RecipeEntity
import com.exercise.domain.usecase.AddRecipeToFavoritesUseCase
import com.exercise.domain.usecase.GetRecipesFavoriteUseCase
import com.exercise.domain.usecase.GetUpdatedRecipesUseCase
import com.exercise.domain.usecase.RemoveRecipeFavoritesUseCase
import com.exercise.recetas.presentation.mapper.RecipeMapper
import com.exercise.recetas.presentation.model.Recipe
import com.exercise.recetas.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getUpdatedRecipesUseCase: GetUpdatedRecipesUseCase,
    private val getRecipesFavoriteUseCase: GetRecipesFavoriteUseCase,
    private val addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
    private val removeRecipeFavoritesUseCase: RemoveRecipeFavoritesUseCase,
    private val recipeMapper: RecipeMapper
) : BaseViewModel() {

    private val _recipesNew = MutableLiveData<List<RecipeEntity>>()
    val recipesNew: LiveData<List<Recipe>> = _recipesNew.switchMap {
        liveData {
            emit(recipeMapper.recipeListDomainToApp(it))
        }
    }

    private val _recipesFav = MutableLiveData<List<RecipeEntity>>()
    val recipesFav: LiveData<List<Recipe>> = _recipesFav.switchMap {
        liveData {
            emit(recipeMapper.recipeListDomainToApp(it))
        }
    }

    fun fetchRecipes() = executeSuspend {
        _recipesNew.value = getUpdatedRecipesUseCase.execute()
    }

    fun getRecipesFavorite() {
        executeSuspend {
            _recipesFav.value = getRecipesFavoriteUseCase.execute()
        }
    }

    fun updateRecipeFavorite(recipe: Recipe) {
        executeSuspend {
            if (recipe.isFavorite) {
                removeRecipeFavoritesUseCase.execute(recipeMapper.recipeAppToDomain(recipe))
            } else {
                addRecipeToFavoritesUseCase.execute(recipeMapper.recipeAppToDomain(recipe))
            }
            _recipesNew.value = _recipesNew.value?.map { currentRecipe ->
                if (currentRecipe.id == recipe.id) {
                    currentRecipe.copy(isFavorite = !currentRecipe.isFavorite)
                } else {
                    currentRecipe
                }
            }
            _recipesFav.value = getRecipesFavoriteUseCase.execute()
        }
    }

}