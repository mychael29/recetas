package com.exercise.data.repository

import com.exercise.data.database.RecipeDao
import com.exercise.data.database.RecipeEntityDb
import com.exercise.data.model.RecipeResponse
import com.exercise.data.remote.RecipeApi
import com.exercise.domain.entity.RecipeEntity
import com.exercise.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun getRecipes(): List<RecipeEntity> {
        return recipeApi.getRecipes().map { it.toDomain() }
    }

    override suspend fun getFavoriteRecipes(): List<RecipeEntity> {
        return recipeDao.getFavorites().map { it.toDomain() }
    }

    override suspend fun addRecipeToFavorites(recipeEntity: RecipeEntity) {
        return recipeDao.insertFavorite(recipeEntity.toData())
    }

    override suspend fun removeRecipeFromFavorites(recipeEntity: RecipeEntity) {
        return recipeDao.deleteFavorite(recipeEntity.toData())
    }

    private fun RecipeResponse.toDomain(): RecipeEntity {
        return RecipeEntity(
            id = id,
            name = name,
            description = description,
            image = imageUrl,
            isFavorite = false
        )
    }

    private fun RecipeEntityDb.toDomain(): RecipeEntity {
        return RecipeEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image,
            isFavorite = true
        )
    }

    private fun RecipeEntity.toData(): RecipeEntityDb {
        return RecipeEntityDb(
            id = this.id,
            name = this.name,
            description = this.description,
            image = this.image
        )
    }
}