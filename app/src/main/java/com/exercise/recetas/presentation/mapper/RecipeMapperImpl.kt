package com.exercise.recetas.presentation.mapper

import com.exercise.domain.entity.RecipeEntity
import com.exercise.recetas.presentation.model.Recipe

class RecipeMapperImpl: RecipeMapper {

    override fun recipeListDomainToApp(entities: List<RecipeEntity>): List<Recipe> {
        return entities.map {
            recipeDomainToApp(it)
        }
    }

    override fun recipeDomainToApp(entity: RecipeEntity): Recipe {
        return Recipe(
            id = entity.id,
            name = entity.name,
            image = entity.image,
            description = entity.description,
            isFavorite = entity.isFavorite
        )
    }

    override fun recipeAppToDomain(recipe: Recipe): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.name,
            image = recipe.image,
            description = recipe.description,
            isFavorite = recipe.isFavorite
        )
    }
}