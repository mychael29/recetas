package com.exercise.data.di

import android.content.Context
import androidx.room.Room
import com.exercise.data.database.AppDatabase
import com.exercise.data.database.RecipeDao
import com.exercise.data.remote.RecipeApi
import com.exercise.data.repository.RecipeRepositoryImpl
import com.exercise.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mocki.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi {
        return retrofit.create(RecipeApi::class.java)
    }

    @Provides
    fun provideRecipeDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "recipe_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao {
        return appDatabase.recipeDao()
    }

    @Provides
    fun provideRecipeRepository(
        recipeApi: RecipeApi,
        recipeDao: RecipeDao
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeApi, recipeDao)
    }
}
