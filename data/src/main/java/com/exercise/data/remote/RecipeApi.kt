package com.exercise.data.remote

import com.exercise.data.model.RecipeResponse
import retrofit2.http.GET

interface RecipeApi {
    @GET("c1ab07e4-0135-45a1-8686-259979e8b303")
    suspend fun getRecipes(): List<RecipeResponse>
}