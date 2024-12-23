package com.exercise.recetas.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.domain.usecase.AddRecipeToFavoritesUseCase
import com.exercise.domain.usecase.RemoveRecipeFavoritesUseCase
import com.exercise.recetas.presentation.mapper.RecipeMapper
import com.exercise.recetas.presentation.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesDetailViewModel @Inject constructor(
    private val addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
    private val removeRecipeFavoritesUseCase: RemoveRecipeFavoritesUseCase,
    private val recipeMapper: RecipeMapper
): ViewModel() {

    private val _recipeEntity = MutableLiveData<Recipe>()
    val recipeEntity: LiveData<Recipe> = _recipeEntity

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun loadRecipe(recipe: Recipe) {
        _recipeEntity.value = recipe
        _isFavorite.value = recipe.isFavorite
    }

    fun toggleFavorite() {
        recipeEntity.value?.let {
            viewModelScope.launch {
                if (it.isFavorite) {
                    removeRecipeFavoritesUseCase.execute(recipeMapper.recipeAppToDomain(it))
                } else {
                    addRecipeToFavoritesUseCase.execute(recipeMapper.recipeAppToDomain(it))
                }
                _isFavorite.value = _isFavorite.value?.not() ?: true
                _recipeEntity.value?.isFavorite = _isFavorite.value ?: false
            }
        }
    }

}