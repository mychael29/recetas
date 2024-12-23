package com.exercise.recetas.presentation.mapper

import com.exercise.domain.entity.RecipeEntity
import com.exercise.recetas.presentation.model.Recipe

interface RecipeMapper {
    fun recipeListDomainToApp(entities: List<RecipeEntity>): List<Recipe>
    fun recipeDomainToApp(entity: RecipeEntity): Recipe
    fun recipeAppToDomain(recipe: Recipe): RecipeEntity
}